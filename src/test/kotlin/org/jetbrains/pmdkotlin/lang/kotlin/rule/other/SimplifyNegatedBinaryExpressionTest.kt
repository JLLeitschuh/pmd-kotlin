package org.jetbrains.pmdkotlin.lang.kotlin.rule.other

import org.junit.Before

/**
 * Created on 22.07.2016.
 */
class SimplifyNegatedBinaryExpressionTest : BaseOther() {

    @Before
    override fun setUp() {
        addRule(RULESET, "SimplifyNegatedBinaryExpression")
    }
}