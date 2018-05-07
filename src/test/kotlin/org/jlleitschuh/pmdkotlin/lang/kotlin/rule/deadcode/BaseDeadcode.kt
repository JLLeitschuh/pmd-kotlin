package org.jlleitschuh.pmdkotlin.lang.kotlin.rule.deadcode

import net.sourceforge.pmd.testframework.SimpleAggregatorTst
import org.junit.Ignore

@Ignore("Its a base class for a ruleset")
open class BaseDeadcode : SimpleAggregatorTst() {

    protected val RULESET = "kotlin-deadcode"
}
