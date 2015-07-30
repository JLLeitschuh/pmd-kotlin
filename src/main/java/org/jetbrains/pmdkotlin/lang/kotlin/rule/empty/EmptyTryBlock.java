package org.jetbrains.pmdkotlin.lang.kotlin.rule.empty;

import org.jetbrains.kotlin.psi.JetTryExpression;
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinNodeAdapter;
import org.jetbrains.pmdkotlin.lang.kotlin.rule.AbstractKotlinRule;

import static org.jetbrains.pmdkotlin.lang.kotlin.rule.empty.EmptyUtils.isEmptyBlock;

public class EmptyTryBlock extends AbstractKotlinRule {
    @Override
    public Object visitTryExpressionPMD(JetTryExpression node, Object data) {
        if (isEmptyBlock(node.getTryBlock())) {
            addViolation(data, new KotlinNodeAdapter(node.getNode()));
        }
        return super.visitTryExpressionPMD(node, data);
    }


}

