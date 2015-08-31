package org.jetbrains.pmdkotlin

import net.sourceforge.pmd.cpd.*
import net.sourceforge.pmd.lang.ParserOptions
import org.jetbrains.kotlin.codegen.FrameMap
import org.jetbrains.pmdkotlin.cpd.KotlinLanguage
import org.jetbrains.pmdkotlin.lang.kotlin.KotlinParser
import org.testng.annotations.BeforeTest
import org.testng.annotations.Test
import java.io.File
import java.io.FileNotFoundException
import java.io.FileReader
import java.io.PrintWriter
import java.net.URISyntaxException
import java.util.*

import org.mockito.Mockito.mock

// todo: add detailed asserts for matches
public class CpdTest {

    var config: CPDConfiguration = CPDConfiguration()
    var cpd: CPD = CPD(config)
    private var hm: MutableMap<String, String>? = null

    BeforeTest fun setUp() {
        config = CPDConfiguration()
        with(config) {
            setLanguage(KotlinLanguage())
            setEncoding("UTF-8")
            setMinimumTileSize(30)
            setIgnoreIdentifiers(true)
            setIgnoreLiterals(true)
        }
        cpd = CPD(config)
    }

//    Test fun parser() {
//        val parser = KotlinParser(ParserOptions())
//        //val file = getResource("deprecatedTest/TraitKeywordTest.kt")
//        val file = getResource("trashTest.kt")
//
//        parser.parse(file.getAbsolutePath(), FileReader(file))
//    }

//    Test fun duplicateFunction() {
//        //cpd.add(getResource("DuplicateFunction.kt"))
//        cpd.add(getResource())
//        cpd.go()
//        show(cpd.getMatches())
//    }
//
//    Test fun ignoreImports() {
//        val parser = KotlinParser(ParserOptions())
//        val file = getResource("ignoreImportsTest/IgnoreImports1.kt")
//        parser.parse(file.getAbsolutePath(), FileReader(file))
//    }
//
    Test fun falsePositives(){
        cpd.add(getResource("cpd/KotlinParserVisitor.kt"))
        cpd.go()
        show(cpd.getMatches())
    }

    //    @Test
    //    public void testDuplicateFunction() throws IOException {
    //        cpd.add(getKotlinFile("DuplicateFunction.kt"));
    //        cpd.go();
    //
    //        show(cpd.getMatches());
    //
    //    }

    //    @Test
    //    public void testIgnoreImports() throws IOException {
    //        cpd.add(getKotlinFile("ignoreImportsTest/IgnoreImports1.kt"));
    //        cpd.add(getKotlinFile("ignoreImportsTest/IgnoreImports2.kt"));
    //
    //        cpd.go();
    //
    //        show(cpd.getMatches());
    //    }
    //
    //    @Test
    //    public void testIgnoreIdentifiersAndLiterals() throws IOException {
    //        cpd.add(getKotlinFile("ignoreIdentifiersAndLiteralsTest/IgnoreIdentifiers.kt"));
    //        cpd.add(getKotlinFile("ignoreIdentifiersAndLiteralsTest/IgnoreIdentifiers2.kt"));
    //
    //        cpd.go();
    //        show(cpd.getMatches());
    //    }

    public fun show(matches: Iterator<Match>) {
        val pw = PrintWriter("cpd_results.txt")
        while (matches.hasNext()) {
            val match = matches.next()
            pw.println(match.toString())

            for (m in match.getMarkSet()) {
                pw.println(m.getBeginLine())
                pw.println(m.getEndLine());
            }
            pw.println(match.getSourceCodeSlice())
        }
        pw.close()
    }
}

