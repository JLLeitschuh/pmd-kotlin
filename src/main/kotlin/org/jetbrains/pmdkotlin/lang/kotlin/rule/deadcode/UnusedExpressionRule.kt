package org.jetbrains.pmdkotlin.lang.kotlin.rule.deadcode

import com.intellij.psi.PsiElement
import net.sourceforge.pmd.lang.ast.Node
import org.jetbrains.kotlin.diagnostics.Errors
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinASTNodeAdapter

public class UnusedExpressionRule: AbstractUnusedExpressionRule(Errors.UNUSED_FUNCTION_LITERAL) {
}