package org.jetbrains.pmdkotlin.lang.kotlin.rule.empty;

import org.jetbrains.kotlin.psi.JetIfExpression;
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinNodeAdapter;
import org.jetbrains.pmdkotlin.lang.kotlin.rule.AbstractKotlinRule;

import static org.jetbrains.pmdkotlin.lang.kotlin.rule.empty.EmptyUtils.isEmptyBlock;

public class EmptyIfStatement extends AbstractKotlinRule {
    @Override
    public Object visitIfExpressionPMD(JetIfExpression node, Object data) {
        if (isEmptyBlock(node.getThen()) || isEmptyBlock(node.getElse())) {
            addViolation(data, new KotlinNodeAdapter(node.getNode()));
        }
        return super.visitIfExpressionPMD(node, data);
    }
}
