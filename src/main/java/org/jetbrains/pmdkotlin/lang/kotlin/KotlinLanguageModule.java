package org.jetbrains.pmdkotlin.lang.kotlin;

import net.sourceforge.pmd.lang.BaseLanguageModule;

public class KotlinLanguageModule extends BaseLanguageModule{
    public static final String NAME = "Kotlin";
    public static final String TERSE_NAME = "kotlin";

    public KotlinLanguageModule() {
        super(NAME, null, TERSE_NAME, null);
        addVersion("0.12", new KotlinHandler(), true);
    }

}
