package org.jlleitschuh.pmdkotlin.lang.kotlin.rule.empty;

import net.sourceforge.pmd.lang.ast.Node;
import org.jetbrains.kotlin.psi.KtWhenEntry;
import org.jetbrains.kotlin.psi.KtWhenExpression;
import org.jlleitschuh.pmdkotlin.lang.kotlin.ast.KotlinASTNodeAdapter;
import org.jlleitschuh.pmdkotlin.lang.kotlin.rule.AbstractKotlinRule;
import org.jlleitschuh.pmdkotlin.lang.kotlin.rule.AbstractKotlinRule;

import static org.jlleitschuh.pmdkotlin.lang.kotlin.rule.empty.EmptyUtils.isEmptyBlock;

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
