package org.jetbrains.pmdkotlin

import net.sourceforge.pmd.lang.ParserOptions
import org.jetbrains.kotlin.psi.KtPackageDirective
import org.jetbrains.pmdkotlin.lang.kotlin.KotlinParser
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinParserVisitor
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.FileReader

public class ParserTest {

    private val parser = KotlinParser(ParserOptions())

    @Test
    public fun testEmptyInput() {
        val file = "".asKtFile()

        parser.parse(file.absolutePath, FileReader(file))
    }

    @Test
    public fun testPackageDirective() {
        val file = "package org.jetbrains.pmdkotlin".asKtFile()
        val adapter = object : KotlinParserVisitor {
            override fun visitPackageDirectivePMD(directive: KtPackageDirective, data: Any?): Any? {
                assertEquals(directive.text, "package org.jetbrains.pmdkotlin")
                return super.visitPackageDirectivePMD(directive, data)
            }
        }

        val root = parser.parse(file.absolutePath, FileReader(file))
        root.jjtAccept(adapter, null)
    }

}