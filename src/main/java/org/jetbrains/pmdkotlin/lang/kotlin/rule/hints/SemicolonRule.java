package org.jetbrains.pmdkotlin.lang.kotlin.rule.hints;

import com.intellij.psi.impl.source.tree.LeafPsiElement;
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinNodeAdapter;
import org.jetbrains.pmdkotlin.lang.kotlin.rule.AbstractKotlinRule;

public class SemicolonRule extends AbstractKotlinRule {

    public Object visit(LeafPsiElement node, Object data) {
        if (node.getText().equals(";")) {
            addViolation(data, new KotlinNodeAdapter(node.getNode()));
        }
        return super.visit(node, data);
    }
}
