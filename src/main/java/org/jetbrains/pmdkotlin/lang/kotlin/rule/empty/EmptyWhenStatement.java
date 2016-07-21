package org.jetbrains.pmdkotlin.lang.kotlin.rule.empty;

import net.sourceforge.pmd.lang.ast.Node;
import org.jetbrains.kotlin.psi.KtWhenEntry;
import org.jetbrains.kotlin.psi.KtWhenExpression;
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinASTNodeAdapter;
import org.jetbrains.pmdkotlin.lang.kotlin.rule.AbstractKotlinRule;

import static org.jetbrains.pmdkotlin.lang.kotlin.rule.empty.EmptyUtils.isEmptyBlock;

public class EmptyWhenStatement extends AbstractKotlinRule {
    @Override
    public Object visitWhenExpressionPMD(KtWhenExpression node, Object data) {
        for (KtWhenEntry expr : node.getEntries()) {
            if (!isEmptyBlock(expr.getExpression())) {
                addViolation(getSavedData(), node.<Node>getCopyableUserData(KotlinASTNodeAdapter.OUTER_NODE_KEY));
                break;
            }
        }

        return super.visitWhenExpressionPMD(node, data);
    }
}
