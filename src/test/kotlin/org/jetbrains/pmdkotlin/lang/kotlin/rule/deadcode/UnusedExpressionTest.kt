package org.jetbrains.pmdkotlin.lang.kotlin.rule.deadcode

import org.junit.Before
import org.junit.Ignore

@Ignore("Stopped working when ported to Kotlin 1.2.31")
class UnusedExpressionTest : BaseDeadcode() {

    @Before
    override fun setUp() {
        addRule(RULESET, "UnusedExpression")
    }
}