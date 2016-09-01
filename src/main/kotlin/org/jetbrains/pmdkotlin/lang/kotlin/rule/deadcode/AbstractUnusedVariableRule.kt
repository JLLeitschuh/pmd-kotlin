package org.jetbrains.pmdkotlin.lang.kotlin.rule.deadcode

import org.jetbrains.kotlin.cfg.ControlFlowInformationProvider
import org.jetbrains.kotlin.diagnostics.DiagnosticFactory
import org.jetbrains.kotlin.psi.KtElement

abstract class AbstractUnusedVariableRule protected constructor(myViolation: DiagnosticFactory<*>) : AbstractDeadcodeRule(myViolation) {

    override fun processElement(element: KtElement) {
        ControlFlowInformationProvider(element, getBindingTrace()).checkDeclaration()
    }
}
