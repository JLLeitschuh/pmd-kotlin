package org.jetbrains.pmdkotlin.lang.kotlin.rule.deprecated;

import org.jetbrains.kotlin.psi.JetEnumEntry;
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinNodeAdapter;
import org.jetbrains.pmdkotlin.lang.kotlin.rule.AbstractKotlinRule;

import static org.jetbrains.kotlin.resolve.DeclarationsChecker.enumEntryUsesDeprecatedSuperConstructor;

public class DeprecatedEnumSuperConstructor extends AbstractKotlinRule {

    @Override
    public Object visitEnumEntryPMD(JetEnumEntry node, Object data) {
        if (enumEntryUsesDeprecatedSuperConstructor(node)) {
            addViolation(getSavedData(), new KotlinNodeAdapter(node.getNode()));
        }

        return super.visitEnumEntryPMD(node, data);
    }
}
