package org.jetbrains.pmdkotlin.lang.kotlin;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.impl.PsiBuilderImpl;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.local.CoreLocalFileSystem;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.impl.source.tree.FileElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import net.sourceforge.pmd.lang.AbstractParser;
import net.sourceforge.pmd.lang.ParserOptions;
import net.sourceforge.pmd.lang.TokenManager;
import net.sourceforge.pmd.lang.ast.ParseException;
import org.apache.commons.io.IOUtils;
import org.jetbrains.kotlin.JetNodeTypes;
import org.jetbrains.kotlin.cli.jvm.compiler.EnvironmentConfigFiles;
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.lexer.JetLexer;
import org.jetbrains.kotlin.parsing.JetParser;
import org.jetbrains.kotlin.parsing.JetParserDefinition;
import org.jetbrains.kotlin.psi.JetFile;
import org.jetbrains.kotlin.psi.stubs.elements.JetFileElementType;
import org.jetbrains.pmdkotlin.lang.kotlin.ast.AbstractKotlinNode;
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinNodeAdapter;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class KotlinParser extends AbstractParser {
    Project project;
    PsiManager manager;
    JetParser parser;
    JetParserDefinition parserDefinition;
    public KotlinParser(ParserOptions parserOptions) {
        super(parserOptions);

        CompilerConfiguration configuration = new CompilerConfiguration();
        Disposable rootDisposable = Disposer.newDisposable();
        KotlinCoreEnvironment environment = KotlinCoreEnvironment.createForProduction(rootDisposable, configuration, EnvironmentConfigFiles.JVM_CONFIG_FILES);

        project = environment.getProject();
        manager = PsiManager.getInstance(project);
        parserDefinition = JetParserDefinition.getInstance();
        parser = (JetParser) parserDefinition.createParser(project);
    }

    @Override
    public TokenManager createTokenManager(Reader source) {
        return new KotlinTokenManager(source);
    }

    @Override
    public boolean canParse() {
        return true;
    }

    private void showTree(ASTNode v, String s) {
        //System.err.println(s + v.getClass());
        if (!(v instanceof FileElement)) {
            System.err.println(s + v.getPsi().getClass());
        }
        for (ASTNode cv : v.getChildren((TokenSet) null)) {
            showTree(cv, s + " ");
        }
    }

    @Override
    public AbstractKotlinNode parse(String fileName, Reader source) throws ParseException {
        VirtualFile vf = new CoreLocalFileSystem().findFileByPath(fileName);
        FileViewProvider provider = manager.findViewProvider(vf);
        PsiFile file = parserDefinition.createFile(provider);
        String s;
        try {
            s = IOUtils.toString(source);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        PsiBuilder builder = new PsiBuilderImpl(project, file, parserDefinition, new JetLexer(), null, s, null, null);

        ASTNode root = parser.parse(JetNodeTypes.JET_FILE, builder, file);
        showTree(root, "");
//        FileElement fe;
//        fe.acceptTree();
        return new KotlinNodeAdapter(root);
    }

    @Override
    public Map<Integer, String> getSuppressMap() {
        return new HashMap<Integer, String>();
    }
}
