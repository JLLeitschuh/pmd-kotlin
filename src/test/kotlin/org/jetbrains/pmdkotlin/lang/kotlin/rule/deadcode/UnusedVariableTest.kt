package org.jetbrains.pmdkotlin.lang.kotlin.rule.deadcode

import net.sourceforge.pmd.testframework.SimpleAggregatorTst
import org.junit.Before

/**
 * Created on 22.07.2016.
 */
class UnusedVariableTest : SimpleAggregatorTst() {

    private val RULESET = "kotlin-deadcode"

    @Before
    override fun setUp() {
        addRule(RULESET, "UnusedVariable")
    }
}