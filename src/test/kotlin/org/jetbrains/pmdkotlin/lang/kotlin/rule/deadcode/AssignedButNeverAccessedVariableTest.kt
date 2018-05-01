package org.jetbrains.pmdkotlin.lang.kotlin.rule.deadcode

import org.junit.Before

class AssignedButNeverAccessedVariableTest : BaseDeadcode() {

    @Before
    override fun setUp() {
        addRule(RULESET, "AssignedButNeverAccessedVariable")
    }
}