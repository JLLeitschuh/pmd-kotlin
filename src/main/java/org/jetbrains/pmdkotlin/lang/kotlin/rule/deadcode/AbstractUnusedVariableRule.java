package org.jetbrains.pmdkotlin.lang.kotlin.rule.deadcode;

import org.jetbrains.kotlin.cfg.JetFlowInformationProvider;
import org.jetbrains.kotlin.diagnostics.DiagnosticFactory;
import org.jetbrains.kotlin.psi.JetElement;

public abstract class AbstractUnusedVariableRule extends AbstractDeadcodeRule {
    protected AbstractUnusedVariableRule(DiagnosticFactory myViolation) {
        super(myViolation);
    }

    @Override
    protected void processElement(JetElement element) {
        (new JetFlowInformationProvider(element, AbstractDeadcodeRule.trace)).markUnusedVariables();
    }
}
