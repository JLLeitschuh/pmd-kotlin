package org.jetbrains.pmdkotlin.lang.kotlin.rule.deadcode;

import com.intellij.psi.PsiElement;
import org.jetbrains.kotlin.cfg.ControlFlowInformationProvider;
import org.jetbrains.kotlin.diagnostics.DiagnosticFactory;
import org.jetbrains.kotlin.psi.KtElement;
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinASTNodeAdapter;

public abstract class AbstractUnusedExpressionRule extends AbstractDeadcodeRule {
    protected AbstractUnusedExpressionRule(DiagnosticFactory myViolation) {
        super(myViolation);
    }

    @Override
    protected void processElement(KtElement element) {
        ControlFlowInformationProvider provider = new ControlFlowInformationProvider(element, getBindingTrace());
    }

    @Override
    protected void addViolation(PsiElement element) {
        KotlinASTNodeAdapter outerNode = (KotlinASTNodeAdapter) element.getCopyableUserData(KotlinASTNodeAdapter.OUTER_NODE_KEY);
        addViolation(getSavedData(), outerNode);
    }
}
