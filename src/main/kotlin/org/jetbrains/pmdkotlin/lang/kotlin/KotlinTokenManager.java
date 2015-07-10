package org.jetbrains.pmdkotlin.lang.kotlin;

import net.sourceforge.pmd.lang.TokenManager;
import org.jetbrains.kotlin.lexer.JetTokens;
import org.jetbrains.kotlin.lexer._JetLexer;

import java.io.IOException;
import java.io.Reader;

public class KotlinTokenManager extends _JetLexer implements TokenManager, JetTokens{
    private static ThreadLocal<String> fileName = new ThreadLocal();
    public KotlinTokenManager(Reader source) {
        super(source);
    }

    public Object getNextToken() {
        try {
            return advance();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void setFileName(String fileName_) {
        this.fileName.set(fileName_);
    }

    public static String getFileName() {
        String fileName_ = (String)fileName.get();
        return fileName_ == null?"(no file name provided)":fileName_;
    }
}
