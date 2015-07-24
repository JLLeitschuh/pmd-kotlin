package org.jetbrains.pmdkotlin.lang.kotlin.ast;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;

public interface KotlinParserVisitor {
    default void visitElement(PsiElement element) {
        System.err.println("Inside visitElement");
        element.acceptChildren((PsiElementVisitor) this);
    }
}
