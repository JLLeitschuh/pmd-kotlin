package org.jetbrains.pmdkotlin.lang.kotlin.ast;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.FileElement;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.dfa.DataFlowNode;
import org.jaxen.JaxenException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.cfg.*;
import org.jetbrains.kotlin.cfg.pseudocode.Pseudocode;
import org.jetbrains.kotlin.diagnostics.Diagnostic;
import org.jetbrains.kotlin.diagnostics.DiagnosticFactory;
import org.jetbrains.kotlin.diagnostics.DiagnosticUtils;
import org.jetbrains.kotlin.diagnostics.Errors;
import org.jetbrains.kotlin.psi.JetElement;
import org.jetbrains.kotlin.resolve.BindingTrace;
import org.jetbrains.kotlin.resolve.BindingTraceContext;
import org.jetbrains.pmdkotlin.lang.kotlin.KotlinFile;
import org.jetbrains.pmdkotlin.lang.kotlin.KotlinParser;
import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;

import static org.jetbrains.kotlin.diagnostics.Errors.UNREACHABLE_CODE;

public class KotlinASTNodeAdapter implements AbstractKotlinNode {
    protected int id;
    // protected KotlinParser parser;
    @NotNull protected KotlinFile kotlinFile;

    protected PsiElement innerNode;
    protected KotlinASTNodeAdapter parentNode;
    protected String image;
    protected KotlinASTNodeAdapter[] children;

    protected int childIndex;

    private DataFlowNode dataFlowNode;
    private Object userData;

    public final static Key<Node> OUTER_NODE_KEY = new Key<Node>("ASTOuterNode");
    public final static Key<Boolean> UNREACHABLE_KEY = new Key<Boolean>("IsUnreachable");
    public KotlinASTNodeAdapter(int id) {
        this.id = id;
    }

    public BindingTrace trace;
    public Pseudocode pseudocode;
    //public PseudocodeVariablesData variablesData;

    public KotlinASTNodeAdapter(int id, KotlinParser parser) {
        this.id = id;
        //this.kotlinFile = kotlinFile;
    }

    public KotlinASTNodeAdapter(PsiElement innerNode, KotlinFile kotlinFile) {
        this(innerNode, kotlinFile, null);
    }

    public KotlinASTNodeAdapter(PsiElement innerNode, KotlinFile kotlinFile, BindingTrace trace) {
        this.kotlinFile = kotlinFile;
        this.innerNode = innerNode;
        this.trace = trace;

        if (this.trace == null) {
            this.trace = new BindingTraceContext();
        }

        if (this.innerNode instanceof JetElement) {
            this.pseudocode = new JetControlFlowProcessor(trace).generatePseudocode((JetElement) this.innerNode);
            //this.flowInfoProvider = new JetFlowInformationProvider((JetElement) innerNode, this.trace);
        }

        this.innerNode.putCopyableUserData(OUTER_NODE_KEY, this);
        this.innerNode.putCopyableUserData(KotlinASTNodeAdapter.UNREACHABLE_KEY, Boolean.FALSE);
    }

//    public PseudocodeVariablesData getPseudocodeVariablesData() {
//        if (variablesData == null && pseudocode != null) {
//            variablesData = new PseudocodeVariablesData(pseudocode, trace.getBindingContext());
//        }
//        return variablesData;
//    }

        @Override
    public Object jjtAccept(KotlinParserVisitor visitor, Object data) {
        innerNode.accept(visitor.toJetVisitor());
        return data;
    }

    @Override
    public Object childrenAccept(KotlinParserVisitor visitor, Object data) {
        childrenPropagation();
        innerNode.acceptChildren(visitor.toJetVisitor());

        return data;
    }

    @Override
    public PsiElement getInnerNode() {
        return innerNode;
    }

    @Override
    public void jjtOpen() {

    }

    @Override
    public void jjtClose() {

    }

    public Class<?> getPsiClass() {
        return innerNode.getClass();
    }

    @Override
    public void jjtSetParent(Node parent) {
        parentNode = (KotlinASTNodeAdapter) parent;
    }

    @Override
    @NotNull
    public Node jjtGetParent() {
//        if (parentNode == null && innerNode != null) {
//            parentNode = new KotlinASTNodeAdapter(innerNode.getParent(), kotlinFile, trace);
//        }

        return parentNode;
    }

    private void childrenPropagation() {
        if (children == null) {
            ASTNode[] innerChildren = innerNode.getNode().getChildren(null);
            children = new KotlinASTNodeAdapter[innerChildren.length];
            for (int i = 0; i < innerChildren.length; i++) {
                children[i] = new KotlinASTNodeAdapter(innerChildren[i].getPsi(), kotlinFile, trace);
                children[i].jjtSetParent(this);
            }
        }
    }


    //Not effective
    @Override
    public void jjtAddChild(Node child, int index) {
        childrenPropagation();
        if (index >= children.length) {
            KotlinASTNodeAdapter[] newChildren = new KotlinASTNodeAdapter[index + 1];
            System.arraycopy(children, 0, newChildren, 0, children.length);
            children = newChildren;
        }

        children[index] = (KotlinASTNodeAdapter)child;
        child.jjtSetChildIndex(index);
    }

    public KotlinASTNodeAdapter[] getChildren() {
        if (children == null) {
            childrenPropagation();
        }

        return children;
    }

    @Override
    public void jjtSetChildIndex(int index) {
        childIndex = index;
    }

    @Override
    public int jjtGetChildIndex() {
        return childIndex;
    }

    @Override
    public Node jjtGetChild(int index) {
        return getChildren()[index];
    }

    @Override
    public int jjtGetNumChildren() {
        return getChildren().length;
    }

    @Override
    public int jjtGetId() {
        return id;
    }

    @Override
    public String getImage() {
        if (this.image == null) {
            image = innerNode.getText();
        }

        return image;
    }

    @Override
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean hasImageEqualTo(String image) {
        String innerImage = getImage();
        return (innerImage != null) && (innerImage.equals(image));
    }

    @Override
    public int getBeginLine() {
        return kotlinFile.getBeginLine(innerNode.getTextRange());
    }

    @Override
    public int getBeginColumn() {
        return kotlinFile.getBeginColumn(innerNode.getTextRange());
    }

    @Override
    public int getEndLine() {
        return kotlinFile.getEndLine(innerNode.getTextRange());
    }

    @Override
    public int getEndColumn() {
        return kotlinFile.getEndColumn(innerNode.getTextRange());
    }

    @Override
    public DataFlowNode getDataFlowNode() {
        return dataFlowNode;
    }

    @Override
    public void setDataFlowNode(DataFlowNode dataFlowNode) {
        this.dataFlowNode = dataFlowNode;
    }

    @Override
    public boolean isFindBoundary() {
        return false;
    }

    @Override
    public Node getNthParent(int n) {
        Node node = jjtGetParent();
        for (int i = 1; i < n; i++) {
            if (node == null) {
                break;
            }
            node = node.jjtGetParent();
        }
        return node;
    }

    @Override
    public <T> T getFirstParentOfType(Class<T> parentType) {
        AbstractKotlinNode pnode = (AbstractKotlinNode) jjtGetParent();
        while (pnode != null && pnode.getPsiClass() != parentType) {
            pnode = (AbstractKotlinNode) pnode.jjtGetParent();
        }

        return (T) pnode;
    }

    @Override
    public <T> List<T> getParentsOfType(Class<T> parentType) {
        List<T> pnodes = new ArrayList<>();
        AbstractKotlinNode pnode = (AbstractKotlinNode) jjtGetParent();
        while (pnode != null) {
            if (pnode.getPsiClass() == parentType) {
                pnodes.add((T) pnode);
            }
            pnode = (AbstractKotlinNode) pnode.jjtGetParent();
        }

        return pnodes;
    }

    @Override
    public <T> List<T> findChildrenOfType(Class<T> childType) {
        List<T> chnodes = new ArrayList<>();
        if (children == null) {
            childrenPropagation();
        }

        for (AbstractKotlinNode aChildren : children) {
            if (aChildren.getPsiClass() == childType) {
                chnodes.add((T) aChildren);
            }
        }

        return chnodes;
    }

    @Override
    public <T> List<T> findDescendantsOfType(Class<T> targetType) {
        List<T> nodes = new ArrayList<>();
        findDescendantsOfType(targetType, nodes, true);

        return nodes;
    }

    @Override
    public <T> void findDescendantsOfType(Class<T> targetType, List<T> results, boolean crossFindBoundaries) {
        findDescendantsOfType(this, targetType, results, crossFindBoundaries);
    }

    private <T> void findDescendantsOfType(Node node, Class<T> targetType, List<T> results, boolean crossFindBoundaries) {
        KotlinASTNodeAdapter[] nodeChildren = ((KotlinASTNodeAdapter)node).getChildren();
        for (KotlinASTNodeAdapter aNodeChildren : nodeChildren) {
            if (aNodeChildren.getPsiClass() == targetType) {
                results.add((T) aNodeChildren);
            }

            findDescendantsOfType(aNodeChildren, targetType, results, crossFindBoundaries);
        }
    }

    @Override
    public <T> T getFirstChildOfType(Class<T> childType) {
        if (children == null) {
            childrenPropagation();
        }

        for (AbstractKotlinNode aChildren : children) {
            if (aChildren.getPsiClass() == childType) {
                return (T) aChildren;
            }
        }
        return null;
    }


    @Override
    public <T> T getFirstDescendantOfType(Class<T> descendantType) {
        return null;
    }

    @Override
    public <T> boolean hasDescendantOfType(Class<T> type) {
        return getFirstDescendantOfType(type) != null;
    }

    @Override
    public List<? extends Node> findChildNodesWithXPath(String xpathString) throws JaxenException {
        return null;
    }

    @Override
    public boolean hasDescendantMatchingXPath(String xpathString) {
        return false;
    }

    @Override
    public Document getAsDocument() {
        return null;
    }

    @Override
    public Object getUserData() {
        return this.userData;
    }

    @Override
    public void setUserData(Object userData) {
        this.userData = userData;
    }




    public PsiElement findElementAt(int offset) {
        return this.innerNode.findElementAt(offset);
    }
}
