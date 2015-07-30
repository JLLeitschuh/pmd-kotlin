package org.jetbrains.pmdkotlin.lang.kotlin.rule.empty;

import net.sourceforge.pmd.RuleContext;
import org.jetbrains.kotlin.psi.JetCatchClause;
import org.jetbrains.kotlin.psi.JetLoopExpression;
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinNodeAdapter;
import org.jetbrains.pmdkotlin.lang.kotlin.rule.AbstractKotlinRule;

import static org.jetbrains.pmdkotlin.lang.kotlin.rule.empty.EmptyUtils.isEmptyBlock;

public class EmptyLoopStatement extends AbstractKotlinRule {
    @Override
    public Object visitLoopExpressionPMD(JetLoopExpression node, Object data) {
        if (isEmptyBlock(node.getBody())) {
            addViolation(getSavedData(), new KotlinNodeAdapter(node.getNode()));
        }
        return super.visitLoopExpressionPMD(node, data);
    }
}
