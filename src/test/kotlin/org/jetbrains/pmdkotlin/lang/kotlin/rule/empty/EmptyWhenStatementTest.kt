package org.jetbrains.pmdkotlin.lang.kotlin.rule.empty

import org.junit.Before

/**
 * Created on 02.09.2016
 */
class EmptyWhenStatementTest : BaseEmpty() {

    @Before
    override fun setUp() {
        addRule(RULESET, "EmptyWhenStatement")
    }
}