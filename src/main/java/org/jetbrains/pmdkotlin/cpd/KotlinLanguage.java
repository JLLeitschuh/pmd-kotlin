package org.jetbrains.pmdkotlin.cpd;

import net.sourceforge.pmd.cpd.AbstractLanguage;

import java.util.Properties;


public class KotlinLanguage extends AbstractLanguage {
    public KotlinLanguage() {
        this(System.getProperties());
    }

    public KotlinLanguage(Properties properties) {
        super("Kotlin", "kotlin", new KotlinTokenizer(), ".kt");
        setProperties(properties);
    }

    public final void setProperties(Properties properties) {
        KotlinTokenizer tokenizer = (KotlinTokenizer)getTokenizer();
        tokenizer.setProperties(properties);
    }
}
