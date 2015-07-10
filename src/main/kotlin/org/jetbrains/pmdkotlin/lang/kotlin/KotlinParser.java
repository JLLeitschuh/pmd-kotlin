package org.jetbrains.pmdkotlin.lang.kotlin;

import net.sourceforge.pmd.lang.AbstractParser;
import net.sourceforge.pmd.lang.ParserOptions;
import net.sourceforge.pmd.lang.TokenManager;
import net.sourceforge.pmd.lang.ast.AbstractTokenManager;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.ast.ParseException;

import java.io.Reader;
import java.util.Map;

public class KotlinParser extends AbstractParser {

    public KotlinParser(ParserOptions parserOptions) {
        super(parserOptions);
    }

    @Override
    public TokenManager createTokenManager(Reader source) {
        return new KotlinTokenManager(source);
    }

    @Override
    public boolean canParse() {
        return false;
    }

    @Override
    public Node parse(String fileName, Reader source) throws ParseException {
        AbstractTokenManager.setFileName(fileName);
        throw new UnsupportedOperationException("parse(Reader) is not supported for Kotlin");
    }

    @Override
    public Map<Integer, String> getSuppressMap() {
        throw new UnsupportedOperationException("getSuppressMap() is not supported for Kotlin");
    }
}
