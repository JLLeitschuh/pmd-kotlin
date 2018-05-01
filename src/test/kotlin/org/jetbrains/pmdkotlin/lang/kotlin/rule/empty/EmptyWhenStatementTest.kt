package org.jetbrains.pmdkotlin.lang.kotlin.rule.empty

import org.junit.Before

class EmptyWhenStatementTest : BaseEmpty() {

    @Before
    override fun setUp() {
        addRule(RULESET, "EmptyWhenStatement")
    }
}