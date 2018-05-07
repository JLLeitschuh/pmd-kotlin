package org.jlleitschuh.pmdkotlin.lang.kotlin.rule.empty;

import net.sourceforge.pmd.lang.ast.Node;
import org.jetbrains.kotlin.psi.KtIfExpression;
import org.jlleitschuh.pmdkotlin.lang.kotlin.ast.KotlinASTNodeAdapter;
import org.jlleitschuh.pmdkotlin.lang.kotlin.rule.AbstractKotlinRule;
import org.jlleitschuh.pmdkotlin.lang.kotlin.ast.KotlinASTNodeAdapter;
import org.jlleitschuh.pmdkotlin.lang.kotlin.rule.AbstractKotlinRule;

import static org.jlleitschuh.pmdkotlin.lang.kotlin.rule.empty.EmptyUtils.isEmptyBlock;

public class EmptyIfStatement extends AbstractKotlinRule {
    @Override
    public Object visitIfExpressionPMD(KtIfExpression node, Object data) {
        if (EmptyUtils.isEmptyBlock(node.getThen()) || EmptyUtils.isEmptyBlock(node.getElse())) {
            addViolation(getSavedData(), node.<Node>getCopyableUserData(KotlinASTNodeAdapter.OUTER_NODE_KEY));
        }
        return super.visitIfExpressionPMD(node, data);
    }
}
