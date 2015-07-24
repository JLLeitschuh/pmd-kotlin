package org.jetbrains.pmdkotlin.lang.kotlin.ast;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.CompositeElement;
import com.intellij.psi.impl.source.tree.FileElement;
import com.intellij.psi.tree.TokenSet;
import net.sourceforge.pmd.lang.ast.AbstractNode;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.dfa.DataFlowNode;
import org.jaxen.JaxenException;
import org.jetbrains.kotlin.psi.JetElement;
import org.jetbrains.pmdkotlin.lang.kotlin.KotlinParser;
import org.w3c.dom.Document;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;

public class KotlinNodeAdapter implements AbstractKotlinNode {
    protected int id;
    protected KotlinParser parser;

    protected ASTNode innerNode;
    protected AbstractKotlinNode parentNode;
    protected String image;
    protected KotlinNodeAdapter[] children;

    protected int childIndex;

    private DataFlowNode dataFlowNode;
    private Object userData;

    public KotlinNodeAdapter(int id) {
        this.id = id;
    }

    public KotlinNodeAdapter(int id, KotlinParser parser) {
        this.id = id;
        this.parser = parser;
    }

    public KotlinNodeAdapter(ASTNode innerNode) {
        this.innerNode = innerNode;
    }

    @Override
    public Object jjtAccept(KotlinParserVisitor visitor, Object data) {
        if (innerNode instanceof FileElement) {
            // (FileElement)((FileElement) innerNode).acceptTree(visitor);
            return null;
        } else {
            innerNode.getPsi().accept((KotlinParserVisitorAdapter) visitor);
        }
        return data;
    }

    @Override
    public Object childrenAccept(KotlinParserVisitor visitor, Object data) {
        if (innerNode instanceof FileElement) {
            for (int i = 0; i < children.length; i++) {
                children[i].jjtAccept(visitor, data);
            }
        } else {
            innerNode.getPsi().acceptChildren((KotlinParserVisitorAdapter) visitor);
        }
        return data;
    }

    @Override
    public void jjtOpen() {

    }

    @Override
    public void jjtClose() {

    }

    public PsiElement getPsi() {
        return (innerNode instanceof FileElement) ? null : innerNode.getPsi();
    }

    public Class<?> getPsiClass() {
        return (innerNode instanceof FileElement) ? null : innerNode.getPsi().getClass();
    }

    @Override
    public void jjtSetParent(Node parent) {
        parentNode = (AbstractKotlinNode) parent;
    }

    @Override
    public Node jjtGetParent() {
        if (parentNode == null) {
            parentNode = new KotlinNodeAdapter(innerNode.getTreeParent());
        }
        return parentNode;
    }

    private void childrenPropagation() {
        if (children == null) {
            ASTNode[] innerChildren = innerNode.getChildren(null);
            children = new KotlinNodeAdapter[innerChildren.length];
            for (int i = 0; i < innerChildren.length; i++) {
                children[i] = new KotlinNodeAdapter(innerChildren[i]);
            }
        }
    }


    //Not effective
    @Override
    public void jjtAddChild(Node child, int index) {
        if (children == null) {
            childrenPropagation();
        } else if (index >= children.length) {
            KotlinNodeAdapter[] newChildren = new KotlinNodeAdapter[index + 1];
            System.arraycopy(children, 0, newChildren, 0, children.length);
            children = newChildren;
        }
        children[index] = (KotlinNodeAdapter)child;
        child.jjtSetChildIndex(index);
    }

    public KotlinNodeAdapter[] getChildren() {
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
        return innerNode.getTextRange().getStartOffset();
    }

    @Override
    public int getBeginColumn() {
        return 0;
    }

    @Override
    public int getEndLine() {
        return innerNode.getTextRange().getEndOffset();
    }

    @Override
    public int getEndColumn() {
        return 0;
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
        for (int i = 0; i < children.length; i++) {
            if (children[i].getClass() == childType) {
                chnodes.add((T) children[i]);
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
        KotlinNodeAdapter[] nodeChildren = ((KotlinNodeAdapter)node).getChildren();
        for (int i = 0; i < nodeChildren.length; i++) {
            if (nodeChildren[i].getPsiClass() == targetType) {
                results.add((T) nodeChildren[i]);
            }

            findDescendantsOfType(nodeChildren[i], targetType, results, crossFindBoundaries);
        }
    }

    @Override
    public <T> T getFirstChildOfType(Class<T> childType) {
        if (children == null) {
            childrenPropagation();
        }

        for (int i = 0; i < children.length; i++) {
            if (children[i].getClass() == childType) {
                return (T) children[i];
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
}
