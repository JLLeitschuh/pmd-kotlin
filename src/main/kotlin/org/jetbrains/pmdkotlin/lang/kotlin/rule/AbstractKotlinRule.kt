package org.jetbrains.pmdkotlin.lang.kotlin.rule

import net.sourceforge.pmd.RuleContext
import net.sourceforge.pmd.lang.LanguageRegistry
import net.sourceforge.pmd.lang.ast.Node
import net.sourceforge.pmd.lang.rule.AbstractRule
import net.sourceforge.pmd.lang.rule.ImmutableLanguage
import org.jetbrains.pmdkotlin.lang.kotlin.KotlinLanguageModule
import org.jetbrains.pmdkotlin.lang.kotlin.ast.AbstractKotlinNode
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinASTNodeAdapter
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinParserVisitor

open class AbstractKotlinRule : AbstractRule(), KotlinParserVisitor, ImmutableLanguage {

    var savedData: Any? = null

    init {
        super.setLanguage(LanguageRegistry.getLanguage(KotlinLanguageModule.NAME))
    }

    override fun apply(nodes: List<Node>, ctx: RuleContext) {
        savedData = ctx
        visitAll(nodes, ctx)
    }

    protected fun visitAll(nodes: List<Node>, ctx: RuleContext) {
        for (element in nodes) {
            val node = element as KotlinASTNodeAdapter

            visit(node, ctx)
        }
    }

    protected fun visit(node: Node, data: Any): Any? {
        (node as AbstractKotlinNode).jjtAccept(this, data)
        return data
    }
}
