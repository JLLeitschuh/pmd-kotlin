package org.jlleitschuh.pmdkotlin.lang.kotlin.rule.deadcode

import org.junit.Before
import org.junit.Ignore

@Ignore("Stopped working when ported to Kotlin 1.2.31")
class UnusedParameterTest : BaseDeadcode() {

    @Before
    override fun setUp() {
        addRule(RULESET, "UnusedParameter")
    }
}