package org.jetbrains.pmdkotlin.lang.kotlin;

import net.sourceforge.pmd.lang.BaseLanguageModule;
import org.jetbrains.pmdkotlin.lang.kotlin.rule.KotlinRuleChainVisitor;

public class KotlinLanguageModule extends BaseLanguageModule{
    public static final String NAME = "Kotlin";
    public static final String TERSE_NAME = "kotlin";

    private static final String FILE_EXTENSION = "kt";

    public KotlinLanguageModule() {
        super(NAME, null, TERSE_NAME, KotlinRuleChainVisitor.class, FILE_EXTENSION);
        addVersion("1.0.3", new KotlinHandler(), true);
    }

}
