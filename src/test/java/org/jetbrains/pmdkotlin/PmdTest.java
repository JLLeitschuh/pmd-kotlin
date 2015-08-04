package org.jetbrains.pmdkotlin;

import net.sourceforge.pmd.PMD;
import net.sourceforge.pmd.PMDConfiguration;
import org.jetbrains.pmdkotlin.lang.kotlin.KotlinLanguageModule;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class PmdTest {

    @BeforeTest
    public void setUp() {}


    @Test
    public void emptyTest() {
        PMDConfiguration config = new PMDConfiguration();
        config.setRuleSets("/home/user/pmd-kotlin/src/main/resources/ruleset/kotlin/empty.xml");
        config.setReportFormat("html");
        config.setInputPaths("/home/user/pmd-kotlin/src/test/resources/org/jetbrains/pmdkotlin/emptyCodeTests/emptyTryCatchBlock.kt");
        config.setThreads(0);
        config.setDefaultLanguageVersion(new KotlinLanguageModule().getDefaultVersion());

        int x = PMD.doPMD(config);
        assert (x == 3);
    }

    @Test
    public void deprecatedTest() {
        PMDConfiguration config = new PMDConfiguration();
        config.setRuleSets("/home/user/pmd-kotlin/src/main/resources/ruleset/kotlin/deprecated.xml");
        config.setReportFormat("html");
        //config.setInputPaths("/home/user/pmd-kotlin/src/test/resources/org/jetbrains/pmdkotlin/deprecatedTest/TraitKeywordTest.kt");
        //config.setInputPaths("/home/user/pmd-kotlin/src/test/resources/org/jetbrains/pmdkotlin/deprecatedTest/EnumSuperConstructorTest.kt");
        //config.setInputPaths("/home/user/pmd-kotlin/src/test/resources/org/jetbrains/pmdkotlin/deprecatedTest/EnumDelimiterTest.kt");
        //config.setInputPaths("/home/user/pmd-kotlin/src/test/resources/org/jetbrains/pmdkotlin/deprecatedTest/LambdaSyntaxTest.kt");
        config.setInputPaths("/home/user/pmd-kotlin/src/test/resources/org/jetbrains/pmdkotlin/deprecatedTest/StaticAssertTest.kt");
        config.setThreads(0);
        config.setDefaultLanguageVersion(new KotlinLanguageModule().getDefaultVersion());
        int x = PMD.doPMD(config);
        assert (x == 1);
    }
}
