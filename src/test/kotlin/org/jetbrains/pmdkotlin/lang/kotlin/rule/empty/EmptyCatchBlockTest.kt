package org.jetbrains.pmdkotlin.lang.kotlin.rule.empty

import org.junit.Before


class EmptyCatchBlockTest : BaseEmpty() {

    @Before
    override fun setUp() {
        addRule(RULESET, "EmptyCatchBlock")
    }
}