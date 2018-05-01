package org.jetbrains.pmdkotlin.lang.kotlin.rule.deadcode

import org.junit.Before


class UnusedValueTest : BaseDeadcode() {

    @Before
    override fun setUp() {
        addRule(RULESET, "UnusedValue")
    }
}