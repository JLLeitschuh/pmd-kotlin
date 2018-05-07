package org.jlleitschuh.pmdkotlin.lang.kotlin.rule.empty;

import net.sourceforge.pmd.lang.ast.Node;
import org.jetbrains.kotlin.psi.KtFinallySection;
import org.jlleitschuh.pmdkotlin.lang.kotlin.ast.KotlinASTNodeAdapter;
import org.jlleitschuh.pmdkotlin.lang.kotlin.rule.AbstractKotlinRule;
import org.jlleitschuh.pmdkotlin.lang.kotlin.rule.AbstractKotlinRule;

import static org.jlleitschuh.pmdkotlin.lang.kotlin.rule.empty.EmptyUtils.isEmptyBlock;

public class EmptyFinallyBlock extends AbstractKotlinRule {
    @Override
    public Object visitFinallySectionPMD(KtFinallySection node, Object data) {
        if (isEmptyBlock(node.getFinalExpression())) {
            addViolation(getSavedData(), node.<Node>getCopyableUserData(KotlinASTNodeAdapter.OUTER_NODE_KEY));
        }
        return super.visitFinallySectionPMD(node, data);
    }
}
