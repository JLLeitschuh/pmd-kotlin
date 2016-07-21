package org.jetbrains.pmdkotlin.lang.kotlin.rule.deadcode;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.diagnostics.Diagnostic;
import org.jetbrains.kotlin.diagnostics.DiagnosticFactory;
import org.jetbrains.kotlin.psi.KtElement;
import org.jetbrains.kotlin.resolve.BindingTrace;
import org.jetbrains.kotlin.resolve.BindingTraceContext;
import org.jetbrains.pmdkotlin.lang.kotlin.rule.AbstractKotlinRule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractDeadcodeRule extends AbstractKotlinRule {
    protected static final BindingTrace trace = new BindingTraceContext() {
        @Override
        public void report(@NotNull Diagnostic diagnostic) {
            DiagnosticFactory<?> factory = diagnostic.getFactory();
            PsiElement element = diagnostic.getPsiElement();
            if (!violations.containsKey(element)) {
                violations.put(element, new HashSet<DiagnosticFactory<?>>());
            }
            violations.get(element).add(factory);
        }
    };

    protected static Map<PsiElement, Set<DiagnosticFactory<?>>> violations = new HashMap<>();
    protected static Set<PsiElement> isProcessed = new HashSet<>();
    protected final DiagnosticFactory myViolation;

    protected AbstractDeadcodeRule(DiagnosticFactory myViolation) {
        this.myViolation = myViolation;
    }

    protected void processElement(KtElement element) {
    }

    protected abstract void addViolation(PsiElement element);

    @Override
    public void visitElementPMD(PsiElement element) {
        if (element instanceof KtElement) {
            processElement((KtElement) element);

            boolean isViolated = violations.containsKey(element) && violations.get(element).contains(myViolation);
            if (isViolated) {
                addViolation(element);
            }
        }

        super.visitElementPMD(element);
    }
}
