package org.jetbrains.pmdkotlin.lang.kotlin.rule.deadcode

import com.intellij.psi.PsiElement
import net.sourceforge.pmd.lang.ast.Node
import org.jetbrains.kotlin.diagnostics.Errors
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinASTNodeAdapter

public class UnusedChangedValueRule: AbstractUnusedVariableRule(Errors.UNUSED_CHANGED_VALUE) {
    override fun addViolation(element: PsiElement) {
        var outerNode = element.getCopyableUserData<Node>(KotlinASTNodeAdapter.OUTER_NODE_KEY)
        addViolation(savedData, outerNode)
    }
}
