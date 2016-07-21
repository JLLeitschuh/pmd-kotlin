package org.jetbrains.pmdkotlin

import net.sourceforge.pmd.PMD
import net.sourceforge.pmd.PMDConfiguration
import org.jetbrains.pmdkotlin.lang.kotlin.KotlinLanguageModule
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

// todo: assert the report itself
public class DeprecationRulesTest {

    var config: PMDConfiguration = PMDConfiguration()

    @Before fun setUp() {
        config = PMDConfiguration()
        config.ruleSets = getResourcePath("ruleset/deprecated.xml")
        config.reportFormat = "text"
        config.threads = 0
        config.setDefaultLanguageVersion(KotlinLanguageModule().defaultVersion)
    }

    @Test fun deprecatedSuperSuperConstructorEnum() {
        config.inputPaths = getResourcePath("deprecatedTest/EnumSuperConstructorTest.kt")
        val x = PMD.doPMD(config)

        assertEquals (x, 3)
    }

    @Test fun traitKeyword() {
        config.inputPaths = getResourcePath("deprecatedTest/TraitKeywordTest.kt");
        val x = PMD.doPMD(config)

        assertEquals (x, 3)
    }

    @Test fun missingDelimiterInEnum() {
        config.inputPaths = getResourcePath("deprecatedTest/EnumDelimiterTest.kt");
        val x = PMD.doPMD(config)

        assertEquals (x, 2)
    }

    @Test fun preferShortLambdaSyntax() {
        config.inputPaths = getResourcePath("deprecatedTest/LambdaSyntaxTest.kt");
        val x = PMD.doPMD(config)

        assertEquals (x, 1)
    }

    @Test fun staticTypeAssertion() {
        config.inputPaths = getResourcePath("deprecatedTest/StaticAssertTest.kt");
        val x = PMD.doPMD(config)

        assertEquals (x, 1)
    }
}
