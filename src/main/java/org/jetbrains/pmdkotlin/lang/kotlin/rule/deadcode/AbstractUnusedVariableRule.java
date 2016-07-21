package org.jetbrains.pmdkotlin.lang.kotlin.rule.deadcode;

import org.jetbrains.kotlin.cfg.ControlFlowInformationProvider;
import org.jetbrains.kotlin.diagnostics.DiagnosticFactory;
import org.jetbrains.kotlin.psi.KtElement;

public abstract class AbstractUnusedVariableRule extends AbstractDeadcodeRule {
    protected AbstractUnusedVariableRule(DiagnosticFactory myViolation) {
        super(myViolation);
    }

    @Override
    protected void processElement(KtElement element) {
        (new ControlFlowInformationProvider(element, AbstractDeadcodeRule.trace)).checkDeclaration();
    }
}
