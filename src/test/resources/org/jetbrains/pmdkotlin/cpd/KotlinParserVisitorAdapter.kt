package org.jetbrains.pmdkotlin.lang.kotlin.ast;

import net.sourceforge.pmd.lang.ast.Node

public open class KotlinParserVisitorAdapter: KotlinParserVisitor {
    public fun visit(node: Node, data: Any): Any? {
        (node as AbstractKotlinNode).childrenAccept(this, data)
        return data
    }
}



