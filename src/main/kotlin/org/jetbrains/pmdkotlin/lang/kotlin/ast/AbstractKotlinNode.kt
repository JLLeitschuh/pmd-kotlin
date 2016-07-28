package org.jetbrains.pmdkotlin.lang.kotlin.ast

import com.intellij.psi.PsiElement
import net.sourceforge.pmd.lang.ast.Node

interface AbstractKotlinNode : Node {
    fun jjtAccept(visitor: KotlinParserVisitor, data: Any): Any

    fun childrenAccept(visitor: KotlinParserVisitor, data: Any): Any

    val innerNode: PsiElement

    fun <T> getPsiClass(): Class<T>
}
