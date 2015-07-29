package org.jetbrains.pmdkotlin.lang.kotlin.rule.empty;

import org.jetbrains.kotlin.psi.JetFinallySection;
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinNodeAdapter;
import org.jetbrains.pmdkotlin.lang.kotlin.rule.AbstractKotlinRule;

import static org.jetbrains.pmdkotlin.lang.kotlin.rule.empty.EmptyUtils.isEmptyBlock;

public class EmptyFinallyBlock extends AbstractKotlinRule {
    @Override
    public Object visitFinallySection(JetFinallySection node, Object data) {
        if (isEmptyBlock(node.getFinalExpression())) {
            addViolation(data, new KotlinNodeAdapter(node.getNode()));
        }
        return super.visit(node, data);
    }
}
