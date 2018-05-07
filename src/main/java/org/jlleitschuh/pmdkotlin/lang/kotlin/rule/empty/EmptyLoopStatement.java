package org.jlleitschuh.pmdkotlin.lang.kotlin.rule.empty;

import net.sourceforge.pmd.lang.ast.Node;
import org.jetbrains.kotlin.psi.KtLoopExpression;
import org.jlleitschuh.pmdkotlin.lang.kotlin.ast.KotlinASTNodeAdapter;
import org.jlleitschuh.pmdkotlin.lang.kotlin.rule.AbstractKotlinRule;
import org.jlleitschuh.pmdkotlin.lang.kotlin.ast.KotlinASTNodeAdapter;
import org.jlleitschuh.pmdkotlin.lang.kotlin.rule.AbstractKotlinRule;

import static org.jlleitschuh.pmdkotlin.lang.kotlin.rule.empty.EmptyUtils.isEmptyBlock;

public class EmptyLoopStatement extends AbstractKotlinRule {
    @Override
    public Object visitLoopExpressionPMD(KtLoopExpression node, Object data) {
        if (EmptyUtils.isEmptyBlock(node.getBody())) {
            addViolation(getSavedData(), node.<Node>getCopyableUserData(KotlinASTNodeAdapter.OUTER_NODE_KEY));
        }
        return super.visitLoopExpressionPMD(node, data);
    }
}
