package org.jetbrains.pmdkotlin.lang.kotlin.rule.deprecated;

import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import net.sourceforge.pmd.lang.ast.Node;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.psi.KtDeclaration;
import org.jetbrains.kotlin.psi.KtEnumEntry;
import org.jetbrains.kotlin.psi.psiUtil.PsiUtilsKt;
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinASTNodeAdapter;
import org.jetbrains.pmdkotlin.lang.kotlin.rule.AbstractKotlinRule;

public class EnumDelimiter extends AbstractKotlinRule {

    @Override
    public Object visitEnumEntryPMD(KtEnumEntry node, Object data) {
        String neededDelimiter = enumEntryExpectedDelimiter(node);
        if (!neededDelimiter.isEmpty()) {
            addViolation(getSavedData(), node.<Node>getCopyableUserData(KotlinASTNodeAdapter.OUTER_NODE_KEY), new Object[]{node.getName(), neededDelimiter});
        }

        return super.visitEnumEntryPMD(node, data);
    }

    private String enumEntryExpectedDelimiter(KtEnumEntry node) {
        PsiElement next = node.getNextSibling();
        while (next != null) {
            if (next instanceof KtDeclaration) break;
            next = next.getNextSibling();
        }
        KtDeclaration nextDeclaration = (KtDeclaration) next;
        next = PsiUtilsKt.getNextSiblingIgnoringWhitespaceAndComments(node);
        IElementType nextType = next != null ? next.getNode().getElementType() : null;
        if (nextDeclaration instanceof KtEnumEntry) {
            // Not last
            return nextType != KtTokens.COMMA ? "," : "";
        }
        else {
            if (nextType == KtTokens.COMMA) {
                next = PsiUtilsKt.getNextSiblingIgnoringWhitespaceAndComments(next);
                nextType = next != null ? next.getNode().getElementType() : null;
            }
            return nextType != KtTokens.SEMICOLON && nextType != KtTokens.RBRACE ? ";" : "";
        }
    }


}
