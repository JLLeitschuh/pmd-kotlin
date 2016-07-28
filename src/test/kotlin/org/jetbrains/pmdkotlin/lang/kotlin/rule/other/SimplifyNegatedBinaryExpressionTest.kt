package org.jetbrains.pmdkotlin.lang.kotlin.rule.other

import net.sourceforge.pmd.testframework.SimpleAggregatorTst
import org.junit.Before

/**
 * Created on 22.07.2016.
 */
class SimplifyNegatedBinaryExpressionTest : SimpleAggregatorTst() {

    private val RULESET = "kotlin-other"

    @Before
    override fun setUp() {
        addRule(RULESET, "SimplifyNegatedBinaryExpression")
    }
}