package org.jetbrains.pmdkotlin.lang.kotlin.rule

import com.intellij.psi.PsiElement
import com.intellij.psi.impl.source.tree.FileElement
import net.sourceforge.pmd.RuleContext
import net.sourceforge.pmd.lang.LanguageRegistry
import net.sourceforge.pmd.lang.ast.Node
import net.sourceforge.pmd.lang.rule.AbstractRule
import net.sourceforge.pmd.lang.rule.ImmutableLanguage
import org.jetbrains.kotlin.psi.JetTreeVisitor
import org.jetbrains.pmdkotlin.lang.kotlin.KotlinLanguageModule
import org.jetbrains.pmdkotlin.lang.kotlin.ast.AbstractKotlinNode
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinNodeAdapter
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinParserVisitor
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinParserVisitorAdapter

public open class AbstractKotlinRule : AbstractRule(), KotlinParserVisitor, ImmutableLanguage {
    init {
        super<AbstractRule>.setLanguage(LanguageRegistry.getLanguage(KotlinLanguageModule.NAME))
    }

    override fun apply(nodes: List<Node>, ctx: RuleContext) {
        savedData = ctx
        visitAll(nodes, ctx)
    }

    protected fun visitAll(nodes: List<Node>, ctx: RuleContext) {
        for (element in nodes) {
            val node = element as KotlinNodeAdapter

            visit(node, ctx)
        }
    }

    public fun visit(node: Node, data: Any): Any? {
        (node as AbstractKotlinNode).childrenAccept(this, data)
        return data
    }

    protected var savedData: Any? = null
}
