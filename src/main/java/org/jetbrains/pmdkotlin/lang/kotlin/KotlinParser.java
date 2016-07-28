package org.jetbrains.pmdkotlin.lang.kotlin;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.lang.impl.PsiBuilderImpl;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.FileElement;
import net.sourceforge.pmd.lang.Parser;
import net.sourceforge.pmd.lang.ParserOptions;
import net.sourceforge.pmd.lang.TokenManager;
import net.sourceforge.pmd.lang.ast.ParseException;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.pmdkotlin.lang.kotlin.ast.AbstractKotlinNode;
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinASTNodeAdapter;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class KotlinParser implements Parser {
    private org.jetbrains.kotlin.parsing.KotlinParser parser;

    private final ParserOptions parserOptions;

    public KotlinParser(ParserOptions parserOptions) {
        this.parserOptions = parserOptions;
        PsiParser intendedParser = KotlinFileContext.parserDefinition.createParser(KotlinFileContext.project);
        if (intendedParser instanceof org.jetbrains.kotlin.parsing.KotlinParser) {
            parser = (org.jetbrains.kotlin.parsing.KotlinParser) intendedParser;
        } else {
            throw new IllegalStateException("KotlinParser not found!");
        }
    }


    @Override
    public ParserOptions getParserOptions() {
        return this.parserOptions;
    }

    @Override
    public TokenManager getTokenManager(String s, Reader reader) {
        return new KotlinTokenManager(new KotlinFileContext(s, reader));
    }

    @Override
    public boolean canParse() {
        return true;
    }

    private void showTree(ASTNode v, String s) {
        //System.err.println(s + v.getClass());
        if (!(v instanceof FileElement)) {
            PsiElement psi = v.getPsi();

            System.err.println(s + v.getPsi().getClass());
        }
        for (ASTNode cv : v.getChildren(null)) {
            showTree(cv, s + "  ");
        }
    }

    @Override
    public AbstractKotlinNode parse(String fileName, Reader source) throws ParseException {
        KotlinFileContext kotlinContext = new KotlinFileContext(fileName, source);
        try {
            PsiBuilder builder = new PsiBuilderImpl(KotlinFileContext.project, kotlinContext.psiFile, KotlinFileContext.parserDefinition, new KotlinTokenManager(), null, kotlinContext.sourceCodeToString(), null, null);
            FileElement root = (FileElement) parser.parse(KtNodeTypes.KT_FILE, builder, kotlinContext.psiFile);
            root.setPsi(kotlinContext.psiFile.getNode().getPsi());

//            showTree(root, "Parsed tree");

            return new KotlinASTNodeAdapter(root.getPsi(), kotlinContext);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<Integer, String> getSuppressMap() {
        return new HashMap<Integer, String>();
    }
}
