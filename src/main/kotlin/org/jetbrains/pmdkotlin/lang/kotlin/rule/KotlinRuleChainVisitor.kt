package org.jetbrains.pmdkotlin.lang.kotlin.rule

import net.sourceforge.pmd.Rule
import net.sourceforge.pmd.RuleContext
import net.sourceforge.pmd.lang.ast.Node
import net.sourceforge.pmd.lang.rule.AbstractRuleChainVisitor
import net.sourceforge.pmd.lang.rule.XPathRule
import org.jetbrains.pmdkotlin.lang.kotlin.ast.AbstractKotlinNode
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinParserVisitorAdapter

class KotlinRuleChainVisitor : AbstractRuleChainVisitor() {
    override fun visit(rule: Rule, node: Node, ctx: RuleContext) {
        if (rule is KotlinParserVisitorAdapter) {
            (node as AbstractKotlinNode).jjtAccept(rule, ctx)
        } else {
            (rule as XPathRule).evaluate(node, ctx)
        }
        return
    }

    override fun indexNodes(nodes: List<Node>, ctx: RuleContext) {
        val visitor = KotlinParserVisitorAdapter()

        for (i in nodes.indices) {
            visitor.visit(nodes[i], ctx)
        }

    }
}
