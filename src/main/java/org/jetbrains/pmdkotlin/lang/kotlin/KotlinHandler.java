package org.jetbrains.pmdkotlin.lang.kotlin;

import net.sourceforge.pmd.lang.AbstractLanguageVersionHandler;
import net.sourceforge.pmd.lang.Parser;
import net.sourceforge.pmd.lang.ParserOptions;
import net.sourceforge.pmd.lang.rule.RuleViolationFactory;
import org.jetbrains.pmdkotlin.lang.kotlin.rule.KotlinRuleViolationFactory;

public class KotlinHandler extends AbstractLanguageVersionHandler {

    @Override
    public RuleViolationFactory getRuleViolationFactory() {
        return KotlinRuleViolationFactory.INSTANCE;
    }

    @Override
    public Parser getParser(ParserOptions parserOptions) {
        return new KotlinParser(parserOptions);
    }
}
