package org.jetbrains.pmdkotlin.lang.kotlin.ast;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import net.sourceforge.pmd.lang.ast.Node;

public interface AbstractKotlinNode extends Node {
    Object jjtAccept(KotlinParserVisitor visitor, Object data);

    Object childrenAccept(KotlinParserVisitor visitor, Object data);

    PsiElement getPsi();

    ASTNode getInnerNode();
    <T> Class<T> getPsiClass();
}
