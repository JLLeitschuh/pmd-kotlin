package org.jetbrains.pmdkotlin.cpd;

import net.sourceforge.pmd.cpd.AbstractLanguage;

public class KotlinLanguage extends AbstractLanguage {
    public KotlinLanguage() {
        super("Kotlin", "kotlin", new KotlinTokenizer(), ".kt");
    }
}
