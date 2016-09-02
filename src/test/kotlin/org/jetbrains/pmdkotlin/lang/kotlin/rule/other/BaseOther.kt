package org.jetbrains.pmdkotlin.lang.kotlin.rule.other

import net.sourceforge.pmd.testframework.SimpleAggregatorTst
import org.junit.Ignore

/**
 * Created on 02.09.2016.
 */

@Ignore("Its a base class for a ruleset")
open class BaseOther : SimpleAggregatorTst() {

    protected val RULESET = "kotlin-other"
}
