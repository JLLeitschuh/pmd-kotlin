package org.jetbrains.pmdkotlin.lang.kotlin.rule.deprecated;

import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.kotlin.lexer.JetTokens;
import org.jetbrains.kotlin.psi.JetDeclaration;
import org.jetbrains.kotlin.psi.JetEnumEntry;
import org.jetbrains.kotlin.psi.psiUtil.PsiUtilPackage;
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinNodeAdapter;
import org.jetbrains.pmdkotlin.lang.kotlin.rule.AbstractKotlinRule;

public class EnumDelimiter extends AbstractKotlinRule {

    @Override
    public Object visitEnumEntryPMD(JetEnumEntry node, Object data) {
        String neededDelimiter = enumEntryExpectedDelimiter(node);
        if (!neededDelimiter.isEmpty()) {
            addViolation(getSavedData(), new KotlinNodeAdapter(node.getNode()));
        }

        return super.visitEnumEntryPMD(node, data);
    }

    private String enumEntryExpectedDelimiter(JetEnumEntry node) {
        PsiElement next = node.getNextSibling();
        while (next != null) {
            if (next instanceof JetDeclaration) break;
            next = next.getNextSibling();
        }
        JetDeclaration nextDeclaration = (JetDeclaration) next;
        next = PsiUtilPackage.getNextSiblingIgnoringWhitespaceAndComments(node);
        IElementType nextType = next != null ? next.getNode().getElementType() : null;
        if (nextDeclaration instanceof JetEnumEntry) {
            // Not last
            return nextType != JetTokens.COMMA ? "," : "";
        }
        else {
            if (nextType == JetTokens.COMMA) {
                next = PsiUtilPackage.getNextSiblingIgnoringWhitespaceAndComments(next);
                nextType = next != null ? next.getNode().getElementType() : null;
            }
            return nextType != JetTokens.SEMICOLON && nextType != JetTokens.RBRACE ? ";" : "";
        }
    }


}
