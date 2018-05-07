package org.jlleitschuh.pmdkotlin.lang.kotlin.rule.empty

import org.junit.Before

class EmptyFinallyBlockTest : BaseEmpty() {

    @Before
    override fun setUp() {
        addRule(RULESET, "EmptyFinallyBlock")
    }
}