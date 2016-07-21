package org.jetbrains.pmdkotlin.lang.kotlin;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import net.sourceforge.pmd.lang.TokenManager;
import org.jetbrains.kotlin.lexer.KotlinLexer;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.psi.KtTypeElement;
import org.jetbrains.pmdkotlin.cpd.KotlinToken;

public class KotlinTokenManager extends KotlinLexer implements TokenManager, KtTokens {
    private static ThreadLocal<String> fileName = new ThreadLocal();
    public KotlinFileContext kotlinFileContext;

    public KotlinTokenManager() {
        super();
    }

//    public KotlinTokenManager(Reader source) {
//        super();
//        try {
//            String s = IOUtils.toString(source);
//            start(s, 0, s.length(), 0);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public KotlinTokenManager(KotlinFileContext kotlinFileContext) {
        this.kotlinFileContext = kotlinFileContext;
        String src = this.kotlinFileContext.sourceCodeToString();
        start(src, 0, src.length(), 0);
    }

    public Object getCurrentToken() {
        IElementType tokenType = getTokenType();
        if (tokenType != null) {
            String image = getBufferSequence().subSequence(getTokenStart(), getTokenEnd()).toString();
            TextRange range = new TextRange(getTokenStart(), getTokenEnd());

            return new KotlinToken(image, kotlinFileContext.filename, getTokenStart(), kotlinFileContext.getBeginLine(range), tokenType);
        }
        return null;
    }

    public Object getNextToken() {
        advance();
        return getCurrentToken();
    }

    public void setFileName(String fileName_) {
        fileName.set(fileName_);
    }

    public static String getFileName() {
        String fileName_ = fileName.get();
        return fileName_ == null ? "(no file name provided)" : fileName_;
    }

    public PsiElement findElementAt(int offset) {
        return kotlinFileContext.psiFile.findElementAt(offset);
    }

    public boolean isTypeToken(KotlinToken token) {
        PsiElement leaf = findElementAt(token.ofset);
        return PsiTreeUtil.getParentOfType(leaf, KtTypeElement.class) != null;
    }
}
