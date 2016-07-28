package org.jetbrains.pmdkotlin.lang.kotlin

import net.sourceforge.pmd.lang.AbstractLanguageVersionHandler
import net.sourceforge.pmd.lang.Parser
import net.sourceforge.pmd.lang.ParserOptions
import net.sourceforge.pmd.lang.rule.RuleViolationFactory
import org.jetbrains.pmdkotlin.lang.kotlin.rule.KotlinRuleViolationFactory

class KotlinHandler : AbstractLanguageVersionHandler() {

    override fun getRuleViolationFactory(): RuleViolationFactory {
        return KotlinRuleViolationFactory.INSTANCE
    }

    override fun getParser(parserOptions: ParserOptions): Parser {
        return KotlinParser(parserOptions)
    }
}
