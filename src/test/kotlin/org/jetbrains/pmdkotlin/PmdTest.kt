package org.jetbrains.pmdkotlin

import net.sourceforge.pmd.PMD
import net.sourceforge.pmd.PMDConfiguration
import org.jetbrains.pmdkotlin.lang.kotlin.KotlinLanguageModule
import org.testng.annotations.BeforeTest
import org.testng.annotations.Test


public class PmdTest {

    BeforeTest
    public fun setUp() {
    }

    Test
    public fun emptyTest() {
        val config = PMDConfiguration()
        config.setRuleSets(getResourcePath("ruleset/empty.xml"))
        config.setReportFormat("html")
        config.setInputPaths(getResourcePath("emptyCodeTests/emptyTryCatchBlock.kt"))
        config.setThreads(0)
        config.setDefaultLanguageVersion(KotlinLanguageModule().getDefaultVersion())

        val x = PMD.doPMD(config)
        assert((x == 3))
    }

    Test
    public fun deprecatedTest() {
        val config = PMDConfiguration()
        config.setRuleSets(getResourcePath("ruleset/deprecated.xml"))
        config.setReportFormat("html")
        //config.setInputPaths("/home/user/pmd-kotlin/src/test/resources/org/jetbrains/pmdkotlin/deprecatedTest/TraitKeywordTest.kt");
        config.setInputPaths(getResourcePath("deprecatedTest/EnumSuperConstructorTest.kt"))
        //config.setInputPaths("/home/user/pmd-kotlin/src/test/resources/org/jetbrains/pmdkotlin/deprecatedTest/EnumDelimiterTest.kt");
        //config.setInputPaths("/home/user/pmd-kotlin/src/test/resources/org/jetbrains/pmdkotlin/deprecatedTest/LambdaSyntaxTest.kt");
        //config.setInputPaths("/home/user/pmd-kotlin/src/test/resources/org/jetbrains/pmdkotlin/deprecatedTest/StaticAssertTest.kt");
        config.setThreads(0)
        config.setReportFile("atata.html")
        config.setDefaultLanguageVersion(KotlinLanguageModule().getDefaultVersion())
        val x = PMD.doPMD(config)
        //        assert (x == 1);
    }
}
