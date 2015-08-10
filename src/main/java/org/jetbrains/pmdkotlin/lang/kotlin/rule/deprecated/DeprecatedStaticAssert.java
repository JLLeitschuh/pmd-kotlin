package org.jetbrains.pmdkotlin.lang.kotlin.rule.deprecated;

import com.intellij.psi.tree.IElementType;
import net.sourceforge.pmd.lang.ast.Node;
import org.jetbrains.kotlin.lexer.JetTokens;
import org.jetbrains.kotlin.psi.JetBinaryExpressionWithTypeRHS;
import org.jetbrains.kotlin.psi.JetSimpleNameExpression;
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinASTNodeAdapter;
import org.jetbrains.pmdkotlin.lang.kotlin.rule.AbstractKotlinRule;

public class DeprecatedStaticAssert extends AbstractKotlinRule {

    @Override
    public Object visitBinaryWithTypeRHSExpressionPMD(JetBinaryExpressionWithTypeRHS node, Object data) {
        JetSimpleNameExpression operationSign = node.getOperationReference();
        IElementType operationType = operationSign.getReferencedNameElementType();
        if (operationType == JetTokens.COLON) {
            addViolation(getSavedData(), node.<Node>getCopyableUserData(KotlinASTNodeAdapter.OUTER_NODE_KEY));
        }
        return super.visitBinaryWithTypeRHSExpressionPMD(node, data);
    }
}
