package org.jetbrains.pmdkotlin.lang.kotlin.rule.empty;

import net.sourceforge.pmd.RuleContext;
import org.jetbrains.kotlin.psi.JetFinallySection;
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinNodeAdapter;
import org.jetbrains.pmdkotlin.lang.kotlin.rule.AbstractKotlinRule;

import static org.jetbrains.pmdkotlin.lang.kotlin.rule.empty.EmptyUtils.isEmptyBlock;

public class EmptyFinallyBlock extends AbstractKotlinRule {
    @Override
    public Object visitFinallySectionPMD(JetFinallySection node, Object data) {
        if (isEmptyBlock(node.getFinalExpression())) {
            addViolation(getSavedData(), new KotlinNodeAdapter(node.getNode()));
        }
        return super.visitFinallySectionPMD(node, data);
    }
}
