package org.jetbrains.pmdkotlin.lang.kotlin.rule.deadcode

import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.cfg.JetFlowInformationProvider
import org.jetbrains.kotlin.diagnostics.Diagnostic
import org.jetbrains.kotlin.diagnostics.DiagnosticFactory
import org.jetbrains.kotlin.diagnostics.Errors
import org.jetbrains.kotlin.psi.JetElement

//public abstract class AbstractUnusedVariableRule(myViolation: DiagnosticFactory<Diagnostic>) : AbstractDeadcodeRule(myViolation) {
//    protected override fun processElement(element: JetElement) {
//        JetFlowInformationProvider(element, AbstractDeadcodeRule.trace).markUnusedVariables()
//    }
//}