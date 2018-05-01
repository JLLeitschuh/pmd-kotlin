package org.jetbrains.pmdkotlin.lang.kotlin.rule.deadcode

import org.junit.Before

class UnusedVariableTest : BaseDeadcode() {

    @Before
    override fun setUp() {
        addRule(RULESET, "UnusedVariable")
    }
}