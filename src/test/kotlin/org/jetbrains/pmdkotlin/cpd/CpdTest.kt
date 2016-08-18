package org.jetbrains.pmdkotlin.cpd

import net.sourceforge.pmd.cpd.CPD
import net.sourceforge.pmd.cpd.CPDConfiguration
import net.sourceforge.pmd.cpd.Match
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.pmdkotlin.getResource
import org.junit.Test

class CpdTest {

    @Test
    fun testDuplicate() {
        //Precondition
        val cpd = CPD(setupCpd())
        cpd.add(getResource("DuplCode.kt"))

        //Test
        cpd.go()
        val matches = getDuplicatedCodeMatches(cpd.matches)

        //Verify
//        printDupMatches(matches)
        assertThat(matches.size).isEqualTo(3)
    }

    private fun setupCpd(ignoreLiterals: Boolean = false, ignoreIdentifiers: Boolean = false): CPDConfiguration {
        val config = CPDConfiguration()
        config.language = KotlinLanguage()
        config.encoding = "UTF-8"
        config.minimumTileSize = 20
        config.isIgnoreIdentifiers = ignoreIdentifiers
        config.isIgnoreLiterals = ignoreLiterals

        return config
    }

    private fun printDupMatches(matches: List<Match>) {
        matches.forEach {
            println(it.toString())
            for (marker in it.markSet) {
                println("(" + marker.beginLine + "-" + marker.endLine + ")")
            }
            println(it.sourceCodeSlice)
        }
    }

    private fun getDuplicatedCodeMatches(matches: Iterator<Match>): List<Match> {
        val matchList = mutableListOf<Match>()
        while (matches.hasNext()) {
            matchList.add(matches.next())
        }
        return matchList
    }
}