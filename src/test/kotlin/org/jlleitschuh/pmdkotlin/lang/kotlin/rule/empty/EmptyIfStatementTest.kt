package org.jlleitschuh.pmdkotlin.lang.kotlin.rule.empty

import org.junit.Before

class EmptyIfStatementTest : BaseEmpty() {

    @Before
    override fun setUp() {
        addRule(RULESET, "EmptyIfStatement")
    }
}