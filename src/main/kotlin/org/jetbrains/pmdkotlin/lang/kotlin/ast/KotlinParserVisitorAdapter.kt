package org.jetbrains.pmdkotlin.lang.kotlin.ast;

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.impl.source.tree.FileElement
import net.sourceforge.pmd.lang.ast.Node
import org.jetbrains.kotlin.psi.JetVisitor

public open class KotlinParserVisitorAdapter: KotlinParserVisitor {
    public fun visit(node: Node, data: Any): Any? {
        (node as AbstractKotlinNode).childrenAccept(this, data)
        return data
    }
}



