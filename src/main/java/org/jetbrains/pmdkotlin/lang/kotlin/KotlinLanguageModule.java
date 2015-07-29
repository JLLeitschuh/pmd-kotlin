package org.jetbrains.pmdkotlin.lang.kotlin;

import net.sourceforge.pmd.lang.BaseLanguageModule;
import org.jetbrains.pmdkotlin.lang.kotlin.rule.KotlinRuleChainVisitor;

public class KotlinLanguageModule extends BaseLanguageModule{
    public static final String NAME = "Kotlin";
    public static final String TERSE_NAME = "kotlin";

    public KotlinLanguageModule() {
        super(NAME, null, TERSE_NAME, KotlinRuleChainVisitor.class);
        addVersion("0.12", new KotlinHandler(), true);
    }

}
