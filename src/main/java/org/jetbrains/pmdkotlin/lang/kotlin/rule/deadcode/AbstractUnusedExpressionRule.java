package org.jetbrains.pmdkotlin.lang.kotlin.rule.deadcode;

import com.intellij.psi.PsiElement;
import org.jetbrains.kotlin.cfg.JetFlowInformationProvider;
import org.jetbrains.kotlin.diagnostics.DiagnosticFactory;
import org.jetbrains.kotlin.psi.JetElement;
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinASTNodeAdapter;

public abstract class AbstractUnusedExpressionRule extends AbstractDeadcodeRule {
    protected AbstractUnusedExpressionRule(DiagnosticFactory myViolation) {
        super(myViolation);
    }

    @Override
    protected void processElement(JetElement element) {
        JetFlowInformationProvider provider = new JetFlowInformationProvider(element, AbstractDeadcodeRule.trace);
        provider.markStatements();
        provider.markUnusedExpressions();
    }

    @Override
    protected void addViolation(PsiElement element) {
        KotlinASTNodeAdapter outerNode = (KotlinASTNodeAdapter) element.getCopyableUserData(KotlinASTNodeAdapter.OUTER_NODE_KEY);
        addViolation(getSavedData(), outerNode);
    }
}
