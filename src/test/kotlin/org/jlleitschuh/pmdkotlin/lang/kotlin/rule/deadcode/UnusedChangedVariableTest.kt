package org.jlleitschuh.pmdkotlin.lang.kotlin.rule.deadcode

import net.sourceforge.pmd.testframework.SimpleAggregatorTst
import org.junit.Before
import org.junit.Ignore

@Ignore("Stopped working when ported to Kotlin 1.2.31")
class UnusedChangedVariableTest : SimpleAggregatorTst() {

    private val RULESET = "kotlin-deadcode"

    @Before
    override fun setUp() {
        addRule(RULESET, "UnusedChangedVariable")
    }
}