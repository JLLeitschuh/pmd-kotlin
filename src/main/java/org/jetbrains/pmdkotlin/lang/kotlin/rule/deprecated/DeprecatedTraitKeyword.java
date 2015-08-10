package org.jetbrains.pmdkotlin.lang.kotlin.rule.deprecated;

import com.intellij.lang.ASTNode;
import net.sourceforge.pmd.lang.ast.Node;
import org.jetbrains.kotlin.lexer.JetTokens;
import org.jetbrains.kotlin.psi.JetClass;
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinASTNodeAdapter;
import org.jetbrains.pmdkotlin.lang.kotlin.rule.AbstractKotlinRule;

public class DeprecatedTraitKeyword extends AbstractKotlinRule {

    @Override
    public Object visitClassPMD(JetClass node, Object data) {
        ASTNode traitKeyword = node.getNode().findChildByType(JetTokens.TRAIT_KEYWORD);
        if (traitKeyword != null) {
            addViolation(getSavedData(), node.<Node>getCopyableUserData(KotlinASTNodeAdapter.OUTER_NODE_KEY));
        }
        return super.visitClassPMD(node, data);
    }
}