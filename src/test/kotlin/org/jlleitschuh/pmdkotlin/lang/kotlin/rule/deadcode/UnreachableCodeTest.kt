package org.jlleitschuh.pmdkotlin.lang.kotlin.rule.deadcode

import org.junit.Before

class UnreachableCodeTest : BaseDeadcode() {

    @Before
    override fun setUp() {
        addRule(RULESET, "UnreachableCode")
    }
}