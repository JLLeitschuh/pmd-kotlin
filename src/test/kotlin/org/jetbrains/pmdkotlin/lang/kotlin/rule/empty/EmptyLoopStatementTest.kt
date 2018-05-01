package org.jetbrains.pmdkotlin.lang.kotlin.rule.empty

import org.junit.Before

class EmptyLoopStatementTest : BaseEmpty() {

    @Before
    override fun setUp() {
        addRule(RULESET, "EmptyLoopStatement")
    }
}