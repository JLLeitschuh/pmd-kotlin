package org.jetbrains.pmdkotlin

import net.sourceforge.pmd.cpd.CPD
import net.sourceforge.pmd.cpd.CPDConfiguration
import net.sourceforge.pmd.cpd.CPDListener
import net.sourceforge.pmd.cpd.Match
import net.sourceforge.pmd.lang.ParserOptions
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

public class CpdTest {
    private var cpd: CPD? = null
    private var config: CPDConfiguration? = null
    private var listener: CPDListener? = null
    private var hm: MutableMap<String, String>? = null

    BeforeTest
    public fun setUp() {
        config = CPDConfiguration()
        config?.setLanguage(KotlinLanguage())
        config?.setEncoding("UTF-8")
        config?.setMinimumTileSize(10)
        config?.setIgnoreIdentifiers(true)
        config?.setIgnoreLiterals(true)
        cpd = CPD(config)
        listener = mock<CPDListener>(javaClass<CPDListener>())
        cpd!!.setCpdListener(listener)
    }

    Test
    public fun testParser() {
        val parser = KotlinParser(ParserOptions())
        val file = getResource("deprecatedTest/TraitKeywordTest.kt")
        parser.parse(file.getAbsolutePath(), FileReader(file))
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

    throws(FileNotFoundException::class)
    public fun show(matches: Iterator<Match>) {
        val pw = PrintWriter("test_results.txt")
        hm = HashMap<String, String>()
        for (sc in cpd!!.getSources()) {
            hm!!.put(sc.getFileName(), sc.getCodeBuffer().toString())
        }

        while (matches.hasNext()) {
            val match = matches.next()
            for (m in match.getMarkSet()) {
                m.setSoureCodeSlice(hm!!.get(m.getFilename())?.substring(m.getBeginLine(), m.getEndLine() + 1))
                pw.println(m.getFilename())
                pw.println(m.getSourceCodeSlice())
            }
            pw.println()
            pw.println()
        }
        pw.close()

    }
}

