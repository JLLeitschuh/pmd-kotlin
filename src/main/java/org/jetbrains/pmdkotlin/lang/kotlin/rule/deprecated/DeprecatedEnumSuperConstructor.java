package org.jetbrains.pmdkotlin.lang.kotlin.rule.deprecated;

import net.sourceforge.pmd.lang.ast.Node;
import org.jetbrains.kotlin.psi.JetEnumEntry;
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinASTNodeAdapter;
import org.jetbrains.pmdkotlin.lang.kotlin.rule.AbstractKotlinRule;

import static org.jetbrains.kotlin.resolve.DeclarationsChecker.enumEntryUsesDeprecatedSuperConstructor;

public class DeprecatedEnumSuperConstructor extends AbstractKotlinRule {

    @Override
    public Object visitEnumEntryPMD(JetEnumEntry node, Object data) {
        if (enumEntryUsesDeprecatedSuperConstructor(node)) {
            addViolation(getSavedData(), node.<Node>getCopyableUserData(KotlinASTNodeAdapter.OUTER_NODE_KEY), new Object[]{node.getName()});
        }

        return super.visitEnumEntryPMD(node, data);
    }
}
