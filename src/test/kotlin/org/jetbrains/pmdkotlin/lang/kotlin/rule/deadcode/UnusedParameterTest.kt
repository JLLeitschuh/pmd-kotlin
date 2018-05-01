package org.jetbrains.pmdkotlin.lang.kotlin.rule.deadcode

import org.junit.Before

class UnusedParameterTest : BaseDeadcode() {

    @Before
    override fun setUp() {
        addRule(RULESET, "UnusedParameter")
    }
}