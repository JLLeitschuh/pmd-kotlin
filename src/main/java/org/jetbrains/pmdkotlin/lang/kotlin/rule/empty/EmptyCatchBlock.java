package org.jetbrains.pmdkotlin.lang.kotlin.rule.empty;

import net.sourceforge.pmd.RuleContext;
import org.jetbrains.kotlin.psi.JetCatchClause;
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinNodeAdapter;
import org.jetbrains.pmdkotlin.lang.kotlin.rule.AbstractKotlinRule;

import static org.jetbrains.pmdkotlin.lang.kotlin.rule.empty.EmptyUtils.isEmptyBlock;

public class EmptyCatchBlock extends AbstractKotlinRule {
    @Override
    public Object visitCatchSectionPMD(JetCatchClause node, Object data) {
        if (isEmptyBlock(node.getCatchBody())) {
            addViolation(getSavedData(), new KotlinNodeAdapter(node.getNode()));
        }
        return super.visitCatchSectionPMD(node, data);
    }
}
