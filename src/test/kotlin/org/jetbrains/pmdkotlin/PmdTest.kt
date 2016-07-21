package org.jetbrains.pmdkotlin

import net.sourceforge.pmd.PMD
import net.sourceforge.pmd.PMDConfiguration
import org.jetbrains.pmdkotlin.lang.kotlin.KotlinLanguageModule
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


public class PmdTest {

    @Before
    public fun setUp() {
    }

    @Test
    public fun emptyTest() {
        val config = PMDConfiguration()
        config.setRuleSets(getResourcePath("ruleset/empty.xml"))
        config.setReportFormat("html")

        config.setInputPaths(getResourcePath("emptyCodeTests/"))
        config.setDefaultLanguageVersion(KotlinLanguageModule().getDefaultVersion())

        assertEquals(3, PMD.doPMD(config))
    }

    @Test
    public fun deprecatedTest() {
        val config = PMDConfiguration()
        config.setRuleSets(getResourcePath("ruleset/deprecated.xml"))
        config.setReportFormat("html")
        //config.setReportFile("atata1.html")

        config.setInputPaths(getResourcePath("deprecatedTest/"))

        config.setDefaultLanguageVersion(KotlinLanguageModule().getDefaultVersion())

        assertEquals(10, PMD.doPMD(config))
    }

    @Test
    public fun otherTest() {
        val config = PMDConfiguration()
        config.setRuleSets(getResourcePath("ruleset/deadcode.xml"))
        config.setReportFormat("html")
        //config.setReportFile("atata.html")
        //config.setInputPaths(getResourcePath("deadCodeTests/UnusedFunctionTest.kt"))
        //config.setInputPaths(getResourcePath("deadCodeTests/UnusedVariableTest.kt"))
        config.setInputPaths(getResourcePath("deadCodeTests/UnreachableCodeTest.kt"))

        config.setDefaultLanguageVersion(KotlinLanguageModule().getDefaultVersion())
        assertEquals(5, PMD.doPMD(config))
    }
}
