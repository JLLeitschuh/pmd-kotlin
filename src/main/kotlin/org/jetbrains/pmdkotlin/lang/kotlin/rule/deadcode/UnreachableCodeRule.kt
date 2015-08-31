package org.jetbrains.pmdkotlin.lang.kotlin.rule.deadcode

import com.intellij.psi.PsiElement
import net.sourceforge.pmd.lang.ast.Node
import org.jetbrains.kotlin.cfg.JetFlowInformationProvider
import org.jetbrains.kotlin.diagnostics.Errors
import org.jetbrains.kotlin.psi.JetDeclarationWithBody
import org.jetbrains.kotlin.psi.JetElement
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinASTNodeAdapter

public class UnreachableCodeRule : AbstractDeadcodeRule(Errors.UNREACHABLE_CODE) {
    override fun addViolation(element: PsiElement) {
        var outerNode = element.getCopyableUserData<Node>(KotlinASTNodeAdapter.OUTER_NODE_KEY)
        addViolation(savedData, outerNode, arrayOf(outerNode.getBeginLine(), outerNode.getEndLine()))
    }

    protected override fun processElement(element: JetElement) {
        if (element is JetDeclarationWithBody) {
            JetFlowInformationProvider(element, AbstractDeadcodeRule.trace).checkFunction(null)
        }
    }
}
