package org.jetbrains.pmdkotlin.lang.kotlin.ast;

import com.intellij.psi.PsiElement;
import net.sourceforge.pmd.lang.ast.Node;

public interface AbstractKotlinNode extends Node {
    Object jjtAccept(KotlinParserVisitor visitor, Object data);

    Object childrenAccept(KotlinParserVisitor visitor, Object data);

    PsiElement getInnerNode();
    <T> Class<T> getPsiClass();
}
