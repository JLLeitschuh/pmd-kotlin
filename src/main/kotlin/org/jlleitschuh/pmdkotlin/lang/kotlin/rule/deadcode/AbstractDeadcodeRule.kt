package org.jlleitschuh.pmdkotlin.lang.kotlin.rule.deadcode

import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.diagnostics.Diagnostic
import org.jetbrains.kotlin.diagnostics.DiagnosticFactory
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.resolve.BindingTrace
import org.jetbrains.kotlin.resolve.BindingTraceContext
import org.jlleitschuh.pmdkotlin.lang.kotlin.rule.AbstractKotlinRule

abstract class AbstractDeadcodeRule protected constructor(protected val myViolation: DiagnosticFactory<*>) : AbstractKotlinRule() {

    companion object {
        protected val violations: MutableMap<PsiElement, Set<DiagnosticFactory<*>>> = HashMap()
        private val trace: BindingTrace = object : BindingTraceContext() {
            override fun report(diagnostic: Diagnostic) {
                val factory = diagnostic.factory
                val element = diagnostic.psiElement
                if (!violations.containsKey(element)) {
                    violations.put(element, HashSet<DiagnosticFactory<*>>())
                }
                (violations[element] as HashSet<DiagnosticFactory<*>>).add(factory)
            }
        }
    }

    override fun visitElementPMD(element: PsiElement) {
        if (element is KtElement) {
            processElement(element)

            val isViolated = violations.containsKey(element) && violations[element]!!.contains(myViolation)
            if (isViolated) {
                addViolation(element)
            }
        }

        super.visitElementPMD(element)
    }

    protected open fun processElement(element: KtElement) {
    }

    protected abstract fun addViolation(element: PsiElement)

    /**
     * Provides the binding trace to the outside.
     * Fixme: When Kotlin support protected members in a superclass companion, this method can be removed.
     */
    protected fun getBindingTrace(): BindingTrace {
        return trace
    }
}
