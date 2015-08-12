package org.jetbrains.pmdkotlin

import net.sourceforge.pmd.lang.ParserOptions
import org.jetbrains.kotlin.psi.JetPackageDirective
import org.jetbrains.pmdkotlin.lang.kotlin.KotlinParser
import org.jetbrains.pmdkotlin.lang.kotlin.ast.AbstractKotlinNode
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinASTNodeAdapter
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinParserVisitorAdapter
import org.testng.Assert
import org.testng.annotations.Test

import java.io.*

Test
public class ParserTest {

    private val parser = KotlinParser(ParserOptions())

    Test
    public fun testEmptyInput() {
        val file = "".asKtFile()

        parser.parse(file.getAbsolutePath(), FileReader(file))
    }

    Test
    public fun testPackageDirective() {
        val file = "package org.jetbrains.pmdkotlin".asKtFile()
        val adapter = object : KotlinParserVisitorAdapter() {
            override fun visitPackageDirectivePMD(directive: JetPackageDirective, data: Any?): Any? {
                Assert.assertEquals(directive.getText(), "package org.jetbrains.pmdkotlin")
                return super.visitPackageDirectivePMD(directive, data)
            }
        }

        val root = parser.parse(file.getAbsolutePath(), FileReader(file))
        root.jjtAccept(adapter, null)
    }

}