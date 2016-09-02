package org.jetbrains.pmdkotlin.lang.kotlin.rule.empty

import org.junit.Before

/**
 * Created on 22.07.2016.
 */
class EmptyFinallyBlockTest : BaseEmpty() {

    @Before
    override fun setUp() {
        addRule(RULESET, "EmptyFinallyBlock")
    }
}