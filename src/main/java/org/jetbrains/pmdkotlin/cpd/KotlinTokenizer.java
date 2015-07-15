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
        ignoreLiterals = Boolean.parseBoolean(properties.getProperty(IGNORE_LITERALS, "true"));
        ignoreIdentifiers = Boolean.parseBoolean(properties.getProperty(IGNORE_IDENTIFIERS, "true"));
    }

    public void tokenize(SourceCode sourceCode, Tokens tokenEntries) {
        ignoreIdentifiers = true;
        ignoreLiterals = true;
        String src = sourceCode.getCodeBuffer().toString();

        LanguageVersionHandler languageVersionHandler = LanguageRegistry.getLanguage(KotlinLanguageModule.NAME).getDefaultVersion().getLanguageVersionHandler();
        String fileName = sourceCode.getFileName();
        KotlinTokenManager tokenMgr = (KotlinTokenManager) languageVersionHandler.getParser(languageVersionHandler.getDefaultParserOptions()).getTokenManager(
                fileName, new StringReader(src));

        TokenDiscarder discarder = new TokenDiscarder();
        IElementType currentToken = (IElementType) tokenMgr.getCurrentToken();

        while (currentToken != null) {
            if (currentToken.equals(JetTokens.IDENTIFIER) && src.substring(tokenMgr.getTokenStart(), tokenMgr.getTokenEnd()).equals("import")) {
                currentToken = JetTokens.IMPORT_KEYWORD;
            }

            discarder.updateState(currentToken);
            if (!discarder.isDiscarding()) {
                processToken(tokenEntries, src, fileName, currentToken, tokenMgr.getTokenStart(), tokenMgr.getTokenEnd());
            }
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

    public void setIgnoreLiterals(boolean ignore) {
        this.ignoreLiterals = ignore;
    }

    public void setIgnoreIdentifiers(boolean ignore) {
        this.ignoreIdentifiers = ignore;
    }

    private static class TokenDiscarder {
        public TokenDiscarder() {
            discardingSemicolon = false;
            discardingWhiteSpaces = false;
            discardingComments = false;

            discardingKeywords = false;
            expectedIdentifier = false;
            expectedDot = false;
        }

        public void updateState(IElementType currentToken) {
            skipSemicolon(currentToken);
            skipWhiteSpaces(currentToken);
            skipComments(currentToken);
            skipPackagesAndImports(currentToken);
        }

        private void skipSemicolon(IElementType currentToken) {
            discardingSemicolon = currentToken.equals(JetTokens.SEMICOLON);
        }

        private void skipWhiteSpaces(IElementType currentToken) {
            discardingWhiteSpaces = currentToken.equals(JetTokens.WHITE_SPACE);
        }

        private void skipComments(IElementType currentToken) {
            discardingComments = currentToken.equals(JetTokens.BLOCK_COMMENT) || currentToken.equals(JetTokens.EOL_COMMENT);
        }

        private void skipPackagesAndImports(IElementType currentToken) {
            if (currentToken.equals(JetTokens.PACKAGE_KEYWORD)
                    || currentToken.equals(JetTokens.IMPORT_KEYWORD)
                    || currentToken.equals(JetTokens.AS_KEYWORD)) {
                discardingKeywords = true;
                expectedIdentifier = true;
                expectedDot = false;
            } else if (discardingKeywords && expectedIdentifier && currentToken.equals(JetTokens.IDENTIFIER)) {
                expectedIdentifier = false;
                expectedDot = true;
            } else if (discardingKeywords && expectedIdentifier && currentToken.equals(JetTokens.MUL)) {
                discardingKeywords = false;
            } else if (discardingKeywords && expectedDot) {
                if (currentToken.equals(JetTokens.DOT)) {
                    expectedIdentifier = true;
                    expectedDot = false;
                } else {
                    discardingKeywords = false;
                }
            }
        }

        public boolean isDiscarding() {
            boolean result = discardingComments || discardingWhiteSpaces || discardingSemicolon || discardingKeywords;
            return result;
        }

        private boolean discardingSemicolon;
        private boolean discardingWhiteSpaces;
        private boolean discardingComments;

        private boolean discardingKeywords;
        private boolean expectedIdentifier, expectedDot;
    }
}
