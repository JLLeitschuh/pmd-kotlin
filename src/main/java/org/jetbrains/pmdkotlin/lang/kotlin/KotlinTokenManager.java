package org.jetbrains.pmdkotlin.lang.kotlin;

import net.sourceforge.pmd.lang.TokenManager;
import org.apache.commons.io.IOUtils;
import org.jetbrains.kotlin.lexer.JetLexer;
import org.jetbrains.kotlin.lexer.JetTokens;

import java.io.IOException;
import java.io.Reader;

public class KotlinTokenManager extends JetLexer implements TokenManager, JetTokens {
    private static ThreadLocal<String> fileName = new ThreadLocal();
    public KotlinTokenManager(Reader source) {
        super();
        try {
            String s = IOUtils.toString(source);
            start(s, 0, s.length(), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object getCurrentToken() {
        Object token = getTokenType();
        return token;
    }

    public Object getNextToken() {
        advance();
        Object token = getTokenType();
        return token;
    }

    public void setFileName(String fileName_) {
        this.fileName.set(fileName_);
    }

    public static String getFileName() {
        String fileName_ = (String)fileName.get();
        return fileName_ == null?"(no file name provided)":fileName_;
    }
}
