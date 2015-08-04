package org.jetbrains.pmdkotlin.lang.kotlin.rule.deprecated;

import org.jetbrains.kotlin.psi.JetFunctionLiteralExpression;
import org.jetbrains.kotlin.psi.JetPsiUtil;
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinNodeAdapter;
import org.jetbrains.pmdkotlin.lang.kotlin.rule.AbstractKotlinRule;

public class DeprecatedLambdaSyntax extends AbstractKotlinRule {

    @Override
    public Object visitFunctionLiteralExpressionPMD(JetFunctionLiteralExpression node, Object data) {
        if (node.getFunctionLiteral().hasBody() && JetPsiUtil.isDeprecatedLambdaSyntax(node)) {
            addViolation(getSavedData(), new KotlinNodeAdapter(node.getNode()));
        }

        return super.visitFunctionLiteralExpressionPMD(node, data);
    }
}
