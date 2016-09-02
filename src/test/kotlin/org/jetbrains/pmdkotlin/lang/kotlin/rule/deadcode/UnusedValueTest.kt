package org.jetbrains.pmdkotlin.lang.kotlin.rule.deadcode

import org.junit.Before

/**
 * Created on 02.09.2016
 */
class UnusedValueTest : BaseDeadcode() {

    @Before
    override fun setUp() {
        addRule(RULESET, "UnusedValue")
    }
}