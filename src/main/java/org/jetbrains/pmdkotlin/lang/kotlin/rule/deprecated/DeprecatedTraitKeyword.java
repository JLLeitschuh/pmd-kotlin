package org.jetbrains.pmdkotlin.lang.kotlin.rule.deprecated;

import com.intellij.lang.ASTNode;
import org.jetbrains.kotlin.lexer.JetTokens;
import org.jetbrains.kotlin.psi.JetClass;
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinNodeAdapter;
import org.jetbrains.pmdkotlin.lang.kotlin.rule.AbstractKotlinRule;

public class DeprecatedTraitKeyword extends AbstractKotlinRule {

    @Override
    public Object visitClassPMD(JetClass node, Object data) {
        ASTNode traitKeyword = node.getNode().findChildByType(JetTokens.TRAIT_KEYWORD);
        if (traitKeyword != null) {
            addViolation(getSavedData(), new KotlinNodeAdapter(node.getNode()));
        }
        return super.visitClassPMD(node, data);
    }
}