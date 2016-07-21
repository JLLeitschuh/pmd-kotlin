package org.jetbrains.pmdkotlin

import net.sourceforge.pmd.cpd.CPD
import net.sourceforge.pmd.cpd.CPDConfiguration
import net.sourceforge.pmd.cpd.Match
import org.jetbrains.pmdkotlin.cpd.KotlinLanguage
import org.junit.Before
import org.junit.Test
import java.io.PrintWriter

// todo: add detailed asserts for matches
class CpdTest {

    var config: CPDConfiguration = CPDConfiguration()
    var cpd: CPD = CPD(config)
    private var hm: MutableMap<String, String>? = null

    @Before
    fun setUp() {
        config = CPDConfiguration()
        with(config) {
            language = KotlinLanguage()
            encoding = "UTF-8"
            minimumTileSize = 30
            isIgnoreIdentifiers = true
            isIgnoreLiterals = true
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
@Test
fun falsePositives() {
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

    fun show(matches: Iterator<Match>) {
        val pw = PrintWriter("cpd_results.txt")
        while (matches.hasNext()) {
            val match = matches.next()
            pw.println(match.toString())

            for (m in match.markSet) {
                pw.println(m.beginLine)
                pw.println(m.endLine);
            }
            pw.println(match.sourceCodeSlice)
        }
        pw.close()
    }
}

