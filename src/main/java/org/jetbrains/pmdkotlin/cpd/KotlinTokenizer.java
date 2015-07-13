package org.jetbrains.pmdkotlin.cpd;

import com.intellij.psi.tree.IElementType;
import net.sourceforge.pmd.cpd.SourceCode;
import net.sourceforge.pmd.cpd.TokenEntry;
import net.sourceforge.pmd.cpd.Tokenizer;
import net.sourceforge.pmd.cpd.Tokens;
import net.sourceforge.pmd.lang.LanguageRegistry;
import net.sourceforge.pmd.lang.LanguageVersionHandler;
import net.sourceforge.pmd.lang.TokenManager;
import org.jetbrains.kotlin.lexer.JetSingleValueToken;
import org.jetbrains.kotlin.lexer.JetToken;
import org.jetbrains.kotlin.lexer.JetTokens;
import org.jetbrains.pmdkotlin.lang.kotlin.KotlinLanguageModule;
import org.jetbrains.pmdkotlin.lang.kotlin.KotlinTokenManager;

import java.io.StringReader;
import java.util.Properties;


public class KotlinTokenizer implements Tokenizer {
    private boolean ignoreLiterals;
    private boolean ignoreIdentifiers;

    public void setProperties(Properties properties) {
        ignoreLiterals = Boolean.parseBoolean(properties.getProperty(IGNORE_LITERALS, "false"));
        ignoreIdentifiers = Boolean.parseBoolean(properties.getProperty(IGNORE_IDENTIFIERS, "false"));
    }

    public void tokenize(SourceCode sourceCode, Tokens tokenEntries) {
        String src = sourceCode.getCodeBuffer().toString();

        LanguageVersionHandler languageVersionHandler = LanguageRegistry.getLanguage(KotlinLanguageModule.NAME).getDefaultVersion().getLanguageVersionHandler();
        String fileName = sourceCode.getFileName();
        KotlinTokenManager tokenMgr = (KotlinTokenManager) languageVersionHandler.getParser(languageVersionHandler.getDefaultParserOptions()).getTokenManager(
                fileName, new StringReader(src));
        IElementType currentToken = (IElementType) tokenMgr.getCurrentToken();

        while (currentToken != null) {
            processToken(tokenEntries, src, fileName, currentToken, tokenMgr.getTokenStart(), tokenMgr.getTokenEnd());
            currentToken = (IElementType) tokenMgr.getNextToken();
        }
        tokenEntries.add(TokenEntry.getEOF());
    }

    private void processToken(Tokens tokenEntries, String src, String fileName, IElementType currentToken, int tokenStart, int tokenEnd) {
        String image = new String(src.substring(tokenStart, tokenEnd));

        if (ignoreLiterals
                && (currentToken.equals(JetTokens.BLOCK_COMMENT)
                || currentToken.equals(JetTokens.CHARACTER_LITERAL)
                || currentToken.equals(JetTokens.INTEGER_LITERAL)
                || currentToken.equals(JetTokens.FLOAT_LITERAL))) {
            image = currentToken.toString();
        }

        if (ignoreIdentifiers && currentToken.equals(JetTokens.IDENTIFIER)) {
            image = currentToken.toString();
        }
        tokenEntries.add(new TokenEntry(image, fileName, tokenStart));
    }

}
