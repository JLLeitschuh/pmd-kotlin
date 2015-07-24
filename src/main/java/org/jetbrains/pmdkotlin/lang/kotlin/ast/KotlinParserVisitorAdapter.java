package org.jetbrains.pmdkotlin.lang.kotlin.ast;

import com.intellij.openapi.progress.ProgressIndicatorProvider;
import com.intellij.psi.PsiElement;
import org.jetbrains.kotlin.psi.JetTreeVisitor;
import org.jetbrains.kotlin.psi.JetVisitor;

public class KotlinParserVisitorAdapter extends JetTreeVisitor implements KotlinParserVisitor {
}