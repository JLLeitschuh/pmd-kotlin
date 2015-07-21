package org.jetbrains.pmdkotlin.lang.kotlin.rule;

import net.sourceforge.pmd.Rule;
import net.sourceforge.pmd.RuleContext;
import net.sourceforge.pmd.RuleViolation;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.rule.AbstractRuleViolationFactory;
import net.sourceforge.pmd.lang.rule.ParametricRuleViolation;
import net.sourceforge.pmd.lang.rule.RuleViolationFactory;
import org.jetbrains.pmdkotlin.lang.kotlin.ast.AbstractKotlinNode;

public class KotlinRuleViolationFactory  extends AbstractRuleViolationFactory{
    public static final RuleViolationFactory INSTANCE = new KotlinRuleViolationFactory();

    @Override
    protected RuleViolation createRuleViolation(Rule rule, RuleContext ruleContext, Node node, String message) {
        return new ParametricRuleViolation<AbstractKotlinNode>(rule, ruleContext, (AbstractKotlinNode) node, message);
    }

    @Override
    protected RuleViolation createRuleViolation(Rule rule, RuleContext ruleContext, Node node, String message, int beginLine, int endLine) {
        final ParametricRuleViolation<AbstractKotlinNode> violation = new ParametricRuleViolation<AbstractKotlinNode>(rule,
                ruleContext, (AbstractKotlinNode) node, message);
        violation.setLines(beginLine, endLine);
        return violation;
    }
}
