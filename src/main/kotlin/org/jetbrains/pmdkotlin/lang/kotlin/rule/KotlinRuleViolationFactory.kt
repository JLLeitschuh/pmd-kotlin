package org.jetbrains.pmdkotlin.lang.kotlin.rule

import net.sourceforge.pmd.Rule
import net.sourceforge.pmd.RuleContext
import net.sourceforge.pmd.RuleViolation
import net.sourceforge.pmd.lang.ast.Node
import net.sourceforge.pmd.lang.rule.AbstractRuleViolationFactory
import net.sourceforge.pmd.lang.rule.ParametricRuleViolation
import net.sourceforge.pmd.lang.rule.RuleViolationFactory
import org.jetbrains.pmdkotlin.lang.kotlin.ast.AbstractKotlinNode

class KotlinRuleViolationFactory : AbstractRuleViolationFactory() {

    override fun createRuleViolation(rule: Rule, ruleContext: RuleContext, node: Node, message: String): RuleViolation {
        return ParametricRuleViolation(rule, ruleContext, node as AbstractKotlinNode, message)
    }

    override fun createRuleViolation(rule: Rule, ruleContext: RuleContext, node: Node, message: String, beginLine: Int, endLine: Int): RuleViolation {
        val violation = ParametricRuleViolation(rule,
                ruleContext, node as AbstractKotlinNode, message)
        violation.setLines(beginLine, endLine)
        return violation
    }

    companion object {
        val INSTANCE: RuleViolationFactory = KotlinRuleViolationFactory()
    }
}
