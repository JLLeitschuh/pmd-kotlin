package org.jetbrains.pmdkotlin.lang.kotlin.rule.deadcode

import org.junit.Before

/**
 * Created on 02.09.2016
 */
class VariableWithRedundantInitializerTest : BaseDeadcode() {

    @Before
    override fun setUp() {
        addRule(RULESET, "VariableWithRedundantInitializer")
    }
}