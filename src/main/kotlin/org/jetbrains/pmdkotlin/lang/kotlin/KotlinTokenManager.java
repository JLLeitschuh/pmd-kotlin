package org.jetbrains.pmdkotlin.lang.kotlin;

import net.sourceforge.pmd.lang.TokenManager;
import net.sourceforge.pmd.lang.ast.SimpleCharStream;
import org.jetbrains.kotlin.lexer.JetLexer;
import org.jetbrains.kotlin.lexer.JetTokens;

import java.io.Reader;

public class KotlinTokenManager extends JetLexer implements TokenManager, JetTokens {
    private static ThreadLocal<String> fileName = new ThreadLocal();
    public KotlinTokenManager(Reader source) {
        super();
        start(new SimpleCharStream(source).GetImage(), 0, 0, 0);
    }

    public Object getNextToken() {
        advance();
        return getTokenType();
    }

    public void setFileName(String fileName_) {
        this.fileName.set(fileName_);
    }

    public static String getFileName() {
        String fileName_ = (String)fileName.get();
        return fileName_ == null?"(no file name provided)":fileName_;
    }
}
