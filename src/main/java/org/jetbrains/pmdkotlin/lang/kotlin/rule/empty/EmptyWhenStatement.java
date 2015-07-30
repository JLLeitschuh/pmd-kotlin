package org.jetbrains.pmdkotlin.lang.kotlin.rule.empty;

import org.jetbrains.kotlin.psi.JetWhenEntry;
import org.jetbrains.kotlin.psi.JetWhenExpression;
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinNodeAdapter;
import org.jetbrains.pmdkotlin.lang.kotlin.rule.AbstractKotlinRule;

import static org.jetbrains.pmdkotlin.lang.kotlin.rule.empty.EmptyUtils.isEmptyBlock;

public class EmptyWhenStatement extends AbstractKotlinRule {
    @Override
    public Object visitWhenExpressionPMD(JetWhenExpression node, Object data) {
        for (JetWhenEntry expr: node.getEntries()) {
            if (!isEmptyBlock(expr.getExpression())) {
                addViolation(getSavedData(), new KotlinNodeAdapter(node.getNode()));
                break;
            }
        }

        return super.visitWhenExpressionPMD(node, data);
    }
 }
