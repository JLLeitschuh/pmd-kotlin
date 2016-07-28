package org.jetbrains.pmdkotlin.lang.kotlin.rule.deprecated

import net.sourceforge.pmd.testframework.SimpleAggregatorTst
import org.junit.Before

/**
 * Created on 22.07.2016.
 */
class EnumDelimiterTest : SimpleAggregatorTst() {

    private val RULESET = "kotlin-deprecated"

    @Before
    override fun setUp() {
        addRule(RULESET, "EnumEntryUsesDeprecatedOrNoDelimiter")
    }
}