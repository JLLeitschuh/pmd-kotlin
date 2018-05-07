package org.jlleitschuh.pmdkotlin.lang.kotlin.rule.deadcode

import com.intellij.psi.PsiElement
import net.sourceforge.pmd.lang.ast.Node
import org.jetbrains.kotlin.diagnostics.Errors
import org.jlleitschuh.pmdkotlin.lang.kotlin.ast.KotlinASTNodeAdapter

public class UnusedValueRule : AbstractUnusedVariableRule(Errors.UNUSED_VALUE) {
    override fun addViolation(element: PsiElement) {
        val outerNode = element.getCopyableUserData<Node>(KotlinASTNodeAdapter.OUTER_NODE_KEY)
        addViolation(savedData, outerNode)
    }
}