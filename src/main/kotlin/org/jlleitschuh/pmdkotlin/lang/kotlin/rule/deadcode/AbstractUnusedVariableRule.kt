package org.jlleitschuh.pmdkotlin.lang.kotlin.rule.deadcode

import org.jetbrains.kotlin.cfg.ControlFlowInformationProvider
import org.jetbrains.kotlin.config.LanguageVersionSettingsImpl
import org.jetbrains.kotlin.diagnostics.DiagnosticFactory
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.resolve.checkers.PlatformDiagnosticSuppressor

abstract class AbstractUnusedVariableRule protected constructor(myViolation: DiagnosticFactory<*>) : AbstractDeadcodeRule(myViolation) {

    override fun processElement(element: KtElement) {
        ControlFlowInformationProvider(element, getBindingTrace(), LanguageVersionSettingsImpl.DEFAULT, PlatformDiagnosticSuppressor.Default)
            .checkDeclaration()
    }
}
