package org.jetbrains.pmdkotlin.lang.kotlin.rule.deprecated;

import com.intellij.psi.tree.IElementType;
import net.sourceforge.pmd.lang.ast.Node;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.psi.KtBinaryExpressionWithTypeRHS;
import org.jetbrains.kotlin.psi.KtSimpleNameExpression;
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinASTNodeAdapter;
import org.jetbrains.pmdkotlin.lang.kotlin.rule.AbstractKotlinRule;

public class DeprecatedStaticAssert extends AbstractKotlinRule {

    @Override
    public Object visitBinaryWithTypeRHSExpressionPMD(KtBinaryExpressionWithTypeRHS node, Object data) {
        KtSimpleNameExpression operationSign = node.getOperationReference();
        IElementType operationType = operationSign.getReferencedNameElementType();
        if (operationType == KtTokens.COLON) {
            addViolation(getSavedData(), node.<Node>getCopyableUserData(KotlinASTNodeAdapter.OUTER_NODE_KEY));
        }
        return super.visitBinaryWithTypeRHSExpressionPMD(node, data);
    }
}
