package org.jlleitschuh.pmdkotlin.lang.kotlin.rule.other

import org.junit.Before

class SimplifyNegatedBinaryExpressionTest : BaseOther() {

    @Before
    override fun setUp() {
        addRule(RULESET, "SimplifyNegatedBinaryExpression")
    }
}