package org.jetbrains.pmdkotlin.lang.kotlin.rule.deadcode

import com.intellij.psi.PsiElement
import net.sourceforge.pmd.lang.ast.Node
import org.jetbrains.kotlin.diagnostics.Errors
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinASTNodeAdapter

public class UnusedVariableRule: AbstractUnusedVariableRule(Errors.UNUSED_VARIABLE){
    override fun addViolation(element: PsiElement) {
        var outerNode = element.getCopyableUserData<Node>(KotlinASTNodeAdapter.OUTER_NODE_KEY)
        addViolation(savedData, outerNode)
    }

    override fun processElement(element: KtElement?) {
        super.processElement(element)
    }
}
