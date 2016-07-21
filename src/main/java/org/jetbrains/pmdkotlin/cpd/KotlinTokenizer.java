package org.jetbrains.pmdkotlin.cpd;

import com.intellij.psi.tree.IElementType;
import com.intellij.util.text.CharSequenceReader;
import net.sourceforge.pmd.cpd.SourceCode;
import net.sourceforge.pmd.cpd.TokenEntry;
import net.sourceforge.pmd.cpd.Tokenizer;
import net.sourceforge.pmd.cpd.Tokens;
import net.sourceforge.pmd.lang.LanguageRegistry;
import net.sourceforge.pmd.lang.LanguageVersionHandler;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.pmdkotlin.lang.kotlin.KotlinLanguageModule;
import org.jetbrains.pmdkotlin.lang.kotlin.KotlinParser;
import org.jetbrains.pmdkotlin.lang.kotlin.KotlinTokenManager;

import java.util.Properties;


public class KotlinTokenizer implements Tokenizer {
    public void setProperties(Properties properties) {
        ignoreLiterals = Boolean.parseBoolean(properties.getProperty(IGNORE_LITERALS, "true"));
        ignoreIdentifiers = Boolean.parseBoolean(properties.getProperty(IGNORE_IDENTIFIERS, "true"));
    }

    public void tokenize(SourceCode sourceCode, Tokens tokenEntries) {
        LanguageVersionHandler languageVersionHandler = LanguageRegistry.getLanguage(KotlinLanguageModule.NAME).getDefaultVersion().getLanguageVersionHandler();

        KotlinParser parser = (KotlinParser) languageVersionHandler.getParser(languageVersionHandler.getDefaultParserOptions());

         tokenManager = (KotlinTokenManager) parser.getTokenManager(
                sourceCode.getFileName(), new CharSequenceReader(sourceCode.getCodeBuffer()));

        TokenDiscarder discarder = new TokenDiscarder();
        KotlinToken currentToken = (KotlinToken) tokenManager.getCurrentToken();

        while (currentToken != null) {
            if (currentToken.tokenType.equals(KtTokens.IDENTIFIER) && currentToken.image.equals("import")) {
                currentToken.tokenType = KtTokens.IMPORT_KEYWORD;
            }

            discarder.updateState(currentToken);
            if (!discarder.isDiscarding()) {
                processToken(tokenEntries, currentToken);
            }
            currentToken = (KotlinToken) tokenManager.getNextToken();
        }
        tokenEntries.add(TokenEntry.getEOF());
    }

    private void processToken(Tokens tokenEntries, KotlinToken currentToken) {
        String image = currentToken.image;
        IElementType currentTokenType = currentToken.tokenType;

        boolean isLiteral = (currentTokenType.equals(KtTokens.BLOCK_COMMENT)
                || currentTokenType.equals(KtTokens.CHARACTER_LITERAL)
                || currentTokenType.equals(KtTokens.INTEGER_LITERAL)
                || currentTokenType.equals(KtTokens.FLOAT_LITERAL));

        if ((currentTokenType.equals(KtTokens.IDENTIFIER) && (ignoreIdentifiers && !tokenManager.isTypeToken(currentToken)))
                || (isLiteral && ignoreLiterals)) {
            tokenEntries.add(new TokenEntry(currentToken.tokenType.toString(), currentToken.filename, currentToken.beginLine));
        } else {
            tokenEntries.add(new TokenEntry(currentToken.image, currentToken.filename, currentToken.beginLine));
        }
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

        public void updateState(KotlinToken currentToken) {
            skipSemicolon(currentToken);
            skipWhiteSpaces(currentToken);
            skipComments(currentToken);
            skipPackagesAndImports(currentToken);
        }

        private void skipSemicolon(KotlinToken currentToken) {
            discardingSemicolon = currentToken.tokenType.equals(KtTokens.SEMICOLON);
        }

        private void skipWhiteSpaces(KotlinToken currentToken) {
            discardingWhiteSpaces = currentToken.tokenType.equals(KtTokens.WHITE_SPACE);
        }

        private void skipComments(KotlinToken currentToken) {
            discardingComments = currentToken.tokenType.equals(KtTokens.BLOCK_COMMENT) || currentToken.equals(KtTokens.EOL_COMMENT);
        }

        private void skipPackagesAndImports(KotlinToken currentToken) {
            IElementType currentTokenType = currentToken.tokenType;
            if (currentTokenType.equals(KtTokens.PACKAGE_KEYWORD)
                    || currentTokenType.equals(KtTokens.IMPORT_KEYWORD)
                    || currentTokenType.equals(KtTokens.AS_KEYWORD)) {
                discardingKeywords = true;
                expectedIdentifier = true;
                expectedDot = false;
            } else if (discardingKeywords && expectedIdentifier && currentTokenType.equals(KtTokens.IDENTIFIER)) {
                expectedIdentifier = false;
                expectedDot = true;
            } else if (discardingKeywords && expectedIdentifier && currentTokenType.equals(KtTokens.MUL)) {
                discardingKeywords = false;
            } else if (discardingKeywords && expectedDot) {
                if (currentTokenType.equals(KtTokens.DOT)) {
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

    private KotlinTokenManager tokenManager;

    private boolean ignoreLiterals;
    private boolean ignoreIdentifiers;
}
