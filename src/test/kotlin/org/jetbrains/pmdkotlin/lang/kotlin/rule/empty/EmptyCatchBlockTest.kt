package org.jetbrains.pmdkotlin.lang.kotlin.rule.empty

import net.sourceforge.pmd.testframework.SimpleAggregatorTst
import org.junit.Before

/**
 * Created on 22.07.2016.
 */
class EmptyCatchBlockTest : SimpleAggregatorTst() {

    private val RULESET = "kotlin-empty"

    @Before
    override fun setUp() {
        addRule(RULESET, "EmptyCatchBlock")
    }
}