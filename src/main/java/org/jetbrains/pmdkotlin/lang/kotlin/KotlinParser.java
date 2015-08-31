package org.jetbrains.pmdkotlin.lang.kotlin;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.impl.PsiBuilderImpl;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.FileElement;
import net.sourceforge.pmd.lang.AbstractParser;
import net.sourceforge.pmd.lang.Parser;
import net.sourceforge.pmd.lang.ParserOptions;
import net.sourceforge.pmd.lang.TokenManager;
import net.sourceforge.pmd.lang.ast.ParseException;
import org.jetbrains.kotlin.JetNodeTypes;
import org.jetbrains.kotlin.parsing.JetParser;
import org.jetbrains.pmdkotlin.lang.kotlin.ast.AbstractKotlinNode;
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinASTNodeAdapter;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class KotlinParser implements Parser {
    private JetParser parser;
    protected final ParserOptions parserOptions;

    public KotlinParser(ParserOptions parserOptions) {
        this.parserOptions = parserOptions;
        parser = (JetParser) KotlinFile.parserDefinition.createParser(KotlinFile.project);
    }

//    @Override
//    public TokenManager createTokenManager(Reader source) {
//        return new KotlinTokenManager(source);
//    }


    @Override
    public ParserOptions getParserOptions() {
        return this.parserOptions;
    }

    @Override
    public TokenManager getTokenManager(String s, Reader reader) {
        return new KotlinTokenManager(new KotlinFile(s, reader));
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
        KotlinFile file = new KotlinFile(fileName, source);
        try {
            PsiBuilder builder = new PsiBuilderImpl(file.project, file.psiFile, file.parserDefinition, new KotlinTokenManager(), null, file.sourceCodeToString(), null, null);
            FileElement root = (FileElement) parser.parse(JetNodeTypes.JET_FILE, builder, file.psiFile);
            root.setPsi(file.psiFile.getNode().getPsi());
            return new KotlinASTNodeAdapter(root.getPsi(), file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<Integer, String> getSuppressMap() {
        return new HashMap<Integer, String>();
    }
}
