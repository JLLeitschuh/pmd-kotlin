package org.jlleitschuh.pmdkotlin.lang.kotlin.rule.deadcode

import com.intellij.psi.PsiElement
import net.sourceforge.pmd.lang.ast.Node
import org.jetbrains.kotlin.cfg.ControlFlowInformationProvider
import org.jetbrains.kotlin.config.LanguageVersionSettingsImpl.Companion.DEFAULT
import org.jetbrains.kotlin.diagnostics.Errors
import org.jetbrains.kotlin.psi.KtDeclarationWithBody
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtIfExpression
import org.jetbrains.kotlin.resolve.checkers.PlatformDiagnosticSuppressor
import org.jlleitschuh.pmdkotlin.lang.kotlin.ast.KotlinASTNodeAdapter

class UnreachableCodeRule : AbstractDeadcodeRule(Errors.UNREACHABLE_CODE) {
    override fun addViolation(element: PsiElement) {
        val outerNode = element.getCopyableUserData<Node>(KotlinASTNodeAdapter.OUTER_NODE_KEY)
        addViolation(savedData, outerNode, arrayOf(outerNode?.beginLine, outerNode?.endLine))
    }

    override fun processElement(element: KtElement) {
        if (element is KtDeclarationWithBody) {
            ControlFlowInformationProvider(element, getBindingTrace(), DEFAULT, PlatformDiagnosticSuppressor.Default)
                .checkFunction(null)
        } else if (element is KtIfExpression) {
            val ifExpr = element
            if (ifExpr.condition?.text == "true" || ifExpr.condition?.text == "false") {
                addViolation(ifExpr)
            }
        }
    }
}
