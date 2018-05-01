package org.jetbrains.pmdkotlin.lang.kotlin

import net.sourceforge.pmd.lang.ParserOptions
import org.jetbrains.kotlin.psi.KtPackageDirective
import org.jetbrains.pmdkotlin.asKtFile
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinParserVisitor
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.FileReader

class ParserTest {

    private val parser = KotlinParser(ParserOptions())

    @Test
    fun testEmptyInput() {
        val file = "".asKtFile()

        parser.parse(file.absolutePath, FileReader(file))
    }

    @Test
    fun testPackageDirective() {
        val file = "package org.jetbrains.pmdkotlin".asKtFile()
        val adapter = object : KotlinParserVisitor {
            override fun visitPackageDirectivePMD(directive: KtPackageDirective, data: Any?): Any? {
                assertEquals(directive.text, "package org.jetbrains.pmdkotlin")
                return super.visitPackageDirectivePMD(directive, data)
            }
        }

        val root = parser.parse(file.absolutePath, FileReader(file))
        root.jjtAccept(adapter)
    }
}