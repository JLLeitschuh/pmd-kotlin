package org.jetbrains.pmdkotlin.lang.kotlin.rule.empty;

import net.sourceforge.pmd.lang.ast.Node;
import org.jetbrains.kotlin.psi.JetWhenEntry;
import org.jetbrains.kotlin.psi.JetWhenExpression;
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinASTNodeAdapter;
import org.jetbrains.pmdkotlin.lang.kotlin.rule.AbstractKotlinRule;

import static org.jetbrains.pmdkotlin.lang.kotlin.rule.empty.EmptyUtils.isEmptyBlock;

public class EmptyWhenStatement extends AbstractKotlinRule {
    @Override
    public Object visitWhenExpressionPMD(JetWhenExpression node, Object data) {
        for (JetWhenEntry expr: node.getEntries()) {
            if (!isEmptyBlock(expr.getExpression())) {
                addViolation(getSavedData(), node.<Node>getCopyableUserData(KotlinASTNodeAdapter.OUTER_NODE_KEY));
                break;
            }
        }

        return super.visitWhenExpressionPMD(node, data);
    }
 }
