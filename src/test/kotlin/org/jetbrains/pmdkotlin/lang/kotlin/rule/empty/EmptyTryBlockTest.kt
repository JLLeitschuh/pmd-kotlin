package org.jetbrains.pmdkotlin.lang.kotlin.rule.empty

import org.junit.Before


class EmptyTryBlockTest : BaseEmpty() {

    @Before
    override fun setUp() {
        addRule(RULESET, "EmptyTryBlock")
    }
}