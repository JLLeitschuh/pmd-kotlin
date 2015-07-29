package org.jetbrains.pmdkotlin.lang.kotlin.rule.empty;

import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.PsiCoreCommentImpl;
import com.intellij.psi.impl.source.tree.PsiWhiteSpaceImpl;
import org.jetbrains.kotlin.psi.JetExpression;

public class EmptyUtils {
    static boolean isEmptyBlock(JetExpression body) {
        for (PsiElement child = body.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (!(child instanceof PsiWhiteSpaceImpl)
                    && !(child instanceof PsiCoreCommentImpl)) {
                return false;
            }
        }

        return true;
    }
}
