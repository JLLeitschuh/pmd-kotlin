package org.jetbrains.pmdkotlin.lang.kotlin.rule.deadcode

import org.junit.Before

/**
 * Created on 22.07.2016.
 */
class UnusedVariableTest : BaseDeadcode() {

    @Before
    override fun setUp() {
        addRule(RULESET, "UnusedVariable")
    }
}