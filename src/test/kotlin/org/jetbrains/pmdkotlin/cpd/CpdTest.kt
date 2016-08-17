package org.jetbrains.pmdkotlin.cpd

import net.sourceforge.pmd.cpd.CPD
import net.sourceforge.pmd.cpd.CPDConfiguration
import net.sourceforge.pmd.cpd.Match
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.pmdkotlin.getResource
import org.junit.Before
import org.junit.Test

class CpdTest {

    val config: CPDConfiguration = CPDConfiguration()
    var cpd: CPD? = null

    @Before
    fun setUp() {
        config.language = KotlinLanguage()
        config.encoding = "UTF-8"
        config.minimumTileSize = 10
        config.isIgnoreIdentifiers = true
        config.isIgnoreLiterals = true
        cpd = CPD(config)
    }


    @Test
    fun testDuplicateReal() {
        //Test
        cpd?.add(getResource("KotlinParserVisitor.kt"))
        cpd?.go()
        val matches = getDuplicatedCodeMatches(cpd?.matches!!)

        //Verify
        printDupMatches(matches)
        assertThat(matches.size).isEqualTo(1)
        assertThat(matches[0].markCount).isEqualTo(2)
    }

    @Test
    fun testDuplicate() {
        //Test
        cpd?.add(getResource("DuplCode.kt"))
        cpd?.go()
        val matches = getDuplicatedCodeMatches(cpd?.matches!!)

        //Verify
        printDupMatches(matches)
        assertThat(matches.size).isEqualTo(3)
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