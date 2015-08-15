package org.jetbrains.pmdkotlin

import net.sourceforge.pmd.PMD
import net.sourceforge.pmd.PMDConfiguration
import org.jetbrains.pmdkotlin.lang.kotlin.KotlinLanguageModule
import org.testng.annotations.BeforeTest
import org.testng.annotations.Test
import org.testng.Assert.*

// todo: assert the report itself
public class DeprecationRulesTest {

    var config: PMDConfiguration = PMDConfiguration()

    BeforeTest fun setUp() {
        config = PMDConfiguration()
        config.setRuleSets(getResourcePath("ruleset/deprecated.xml"))
        config.setReportFormat("text")
        config.setThreads(0)
        config.setDefaultLanguageVersion(KotlinLanguageModule().getDefaultVersion())
    }

    Test fun deprecatedSuperSuperConstructorEnum() {
        config.setInputPaths(getResourcePath("deprecatedTest/EnumSuperConstructorTest.kt"))
        val x = PMD.doPMD(config)

        assertEquals (x, 3)
    }

    Test fun traitKeyword() {
        config.setInputPaths(getResourcePath("deprecatedTest/TraitKeywordTest.kt"));
        val x = PMD.doPMD(config)

        assertEquals (x, 3)
    }

    Test fun missingDelimiterInEnum() {
        config.setInputPaths(getResourcePath("deprecatedTest/EnumDelimiterTest.kt"));
        val x = PMD.doPMD(config)

        assertEquals (x, 2)
    }

    Test fun preferShortLambdaSyntax() {
        config.setInputPaths(getResourcePath("deprecatedTest/LambdaSyntaxTest.kt"));
        val x = PMD.doPMD(config)

        assertEquals (x, 1)
    }

    Test fun staticTypeAssertion() {
        config.setInputPaths(getResourcePath("deprecatedTest/StaticAssertTest.kt"));
        val x = PMD.doPMD(config)

        assertEquals (x, 1)
    }
}
