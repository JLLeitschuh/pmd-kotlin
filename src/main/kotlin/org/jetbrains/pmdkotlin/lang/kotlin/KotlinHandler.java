package org.jetbrains.pmdkotlin.lang.kotlin;

import net.sourceforge.pmd.lang.AbstractLanguageVersionHandler;
import net.sourceforge.pmd.lang.Parser;
import net.sourceforge.pmd.lang.ParserOptions;
import net.sourceforge.pmd.lang.rule.RuleViolationFactory;

public class KotlinHandler extends AbstractLanguageVersionHandler {
    @Override
    public RuleViolationFactory getRuleViolationFactory() {
        throw new UnsupportedOperationException("getRuleViolationFactory() is not supported for Kotlin");
    }

    @Override
    public Parser getParser(ParserOptions parserOptions) {
        return new KotlinParser(parserOptions);
    }
}
