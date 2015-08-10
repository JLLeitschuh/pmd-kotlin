package org.jetbrains.pmdkotlin.lang.kotlin.rule.deprecated;

import net.sourceforge.pmd.lang.ast.Node;
import org.jetbrains.kotlin.psi.JetFunctionLiteralExpression;
import org.jetbrains.kotlin.psi.JetPsiUtil;
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinASTNodeAdapter;
import org.jetbrains.pmdkotlin.lang.kotlin.rule.AbstractKotlinRule;

public class DeprecatedLambdaSyntax extends AbstractKotlinRule {

    @Override
    public Object visitFunctionLiteralExpressionPMD(JetFunctionLiteralExpression node, Object data) {
        if (node.getFunctionLiteral().hasBody() && JetPsiUtil.isDeprecatedLambdaSyntax(node)) {
            addViolation(getSavedData(), node.<Node>getCopyableUserData(KotlinASTNodeAdapter.OUTER_NODE_KEY));
        }

        return super.visitFunctionLiteralExpressionPMD(node, data);
    }
}
