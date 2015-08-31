package org.jetbrains.pmdkotlin.cpd;

import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.text.CharSequenceReader;
import net.sourceforge.pmd.cpd.SourceCode;
import net.sourceforge.pmd.cpd.TokenEntry;
import net.sourceforge.pmd.cpd.Tokenizer;
import net.sourceforge.pmd.cpd.Tokens;
import net.sourceforge.pmd.lang.LanguageRegistry;
import net.sourceforge.pmd.lang.LanguageVersionHandler;
import org.jetbrains.kotlin.lexer.JetTokens;
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
            if (currentToken.tokenType.equals(JetTokens.IDENTIFIER) && currentToken.image.equals("import")) {
                currentToken.tokenType = JetTokens.IMPORT_KEYWORD;
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

        boolean isLiteral = (currentTokenType.equals(JetTokens.BLOCK_COMMENT)
                || currentTokenType.equals(JetTokens.CHARACTER_LITERAL)
                || currentTokenType.equals(JetTokens.INTEGER_LITERAL)
                || currentTokenType.equals(JetTokens.FLOAT_LITERAL));

        if ((currentTokenType.equals(JetTokens.IDENTIFIER) && (ignoreIdentifiers && !tokenManager.isTypeToken(currentToken)))
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
            discardingSemicolon = currentToken.tokenType.equals(JetTokens.SEMICOLON);
        }

        private void skipWhiteSpaces(KotlinToken currentToken) {
            discardingWhiteSpaces = currentToken.tokenType.equals(JetTokens.WHITE_SPACE);
        }

        private void skipComments(KotlinToken currentToken) {
            discardingComments = currentToken.tokenType.equals(JetTokens.BLOCK_COMMENT) || currentToken.equals(JetTokens.EOL_COMMENT);
        }

        private void skipPackagesAndImports(KotlinToken currentToken) {
            IElementType currentTokenType = currentToken.tokenType;
            if (currentTokenType.equals(JetTokens.PACKAGE_KEYWORD)
                    || currentTokenType.equals(JetTokens.IMPORT_KEYWORD)
                    || currentTokenType.equals(JetTokens.AS_KEYWORD)) {
                discardingKeywords = true;
                expectedIdentifier = true;
                expectedDot = false;
            } else if (discardingKeywords && expectedIdentifier && currentTokenType.equals(JetTokens.IDENTIFIER)) {
                expectedIdentifier = false;
                expectedDot = true;
            } else if (discardingKeywords && expectedIdentifier && currentTokenType.equals(JetTokens.MUL)) {
                discardingKeywords = false;
            } else if (discardingKeywords && expectedDot) {
                if (currentTokenType.equals(JetTokens.DOT)) {
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
