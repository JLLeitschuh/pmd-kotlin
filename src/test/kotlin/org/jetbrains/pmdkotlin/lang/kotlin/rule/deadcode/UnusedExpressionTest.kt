package org.jetbrains.pmdkotlin.lang.kotlin.rule.deadcode

import org.junit.Before

class UnusedExpressionTest : BaseDeadcode() {

    @Before
    override fun setUp() {
        addRule(RULESET, "UnusedExpression")
    }
}