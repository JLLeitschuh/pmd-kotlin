package org.jlleitschuh.pmdkotlin.lang.kotlin.rule.deadcode

import com.intellij.psi.PsiElement
import net.sourceforge.pmd.lang.ast.Node
import org.jetbrains.kotlin.diagnostics.Errors
import org.jlleitschuh.pmdkotlin.lang.kotlin.ast.KotlinASTNodeAdapter

public class UnusedParameterRule : AbstractUnusedVariableRule(Errors.UNUSED_PARAMETER) {
    override fun addViolation(element: PsiElement) {
        var outerNode = element.getCopyableUserData<Node>(KotlinASTNodeAdapter.OUTER_NODE_KEY)
        addViolation(savedData, outerNode)
    }
}