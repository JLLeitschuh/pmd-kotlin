package org.jetbrains.pmdkotlin.lang.kotlin;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.impl.PsiBuilderImpl;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.editor.Document;
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
import org.jetbrains.kotlin.parsing.*;
import org.jetbrains.kotlin.psi.stubs.elements.JetFileElementType;
import org.jetbrains.pmdkotlin.lang.kotlin.ast.AbstractKotlinNode;
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinASTNodeAdapter;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static org.jetbrains.kotlin.JetNodeTypes.JET_FILE;

public class KotlinParser extends AbstractParser {
    Project project;
    PsiManager manager;
    JetParser parser;
    PsiFile file;
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
            PsiElement psi = v.getPsi();

            System.err.println(s + v.getPsi().getClass());
        }
        for (ASTNode cv : v.getChildren(null)) {
            showTree(cv, s + "  ");
        }
    }

    public Document getDocument() {
        return file.getViewProvider().getDocument();
    }

    @Override
    public AbstractKotlinNode parse(String fileName, Reader source) throws ParseException {
        VirtualFile vf = new CoreLocalFileSystem().findFileByPath(fileName);
        FileViewProvider provider = manager.findViewProvider(vf);
        file = parserDefinition.createFile(provider);
        String s;
        try {
            s = IOUtils.toString(source);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        PsiBuilder builder = new PsiBuilderImpl(project, file, parserDefinition, new JetLexer(), null, s, null, null);

        FileElement root = (FileElement) parser.parse(JetNodeTypes.JET_FILE, builder, file);
        root.setPsi(file.getNode().getPsi());
        return new KotlinASTNodeAdapter((FileElement)root, this);

    /* temporary */

//        PsiBuilder builder = new PsiBuilderImpl(project, file, parserDefinition, new JetLexer(), null, s, null, null);
//        try {
//            Method m = JetParsing.class.getDeclaredMethod("createForTopLevel", SemanticWhitespaceAwarePsiBuilder.class);
//            m.setAccessible(true);
//            JetParsing jp = (JetParsing) m.invoke(null, new SemanticWhitespaceAwarePsiBuilderImpl(builder));
//            m = JetParsing.class.getSuperclass().getDeclaredMethod("mark");
//            m.setAccessible(true);
//            PsiBuilder.Marker fileMarker = (PsiBuilder.Marker) m.invoke(jp);
//
//            m = jp.getClass().getDeclaredMethod("parsePreamble");
//            m.setAccessible(true);
//            m.invoke(jp);
//
//            Method eof = JetParsing.class.getSuperclass().getDeclaredMethod("eof");
//            eof.setAccessible(true);
//
//            m = jp.getClass().getDeclaredMethod("parseTopLevelDeclaration");
//            m.setAccessible(true);
//
//            while (!(Boolean)eof.invoke(jp)) {
//                m.invoke(jp);
//            }
//            fileMarker.done(new JetFileElementType());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        ASTNode root = builder.getTreeBuilt();
//        ASTNode root2 = file.getNode();
//        root2.getPsi();
//        builder = new PsiBuilderImpl(project, file, parserDefinition, new JetLexer(), null, s, null, null);
//        ASTNode root3 = parser.parse(JetNodeTypes.JET_FILE, builder, file);
//        return new KotlinASTNodeAdapter((FileElement) root, this);
    /* temporary end */
    }

    @Override
    public Map<Integer, String> getSuppressMap() {
        return new HashMap<Integer, String>();
    }
}
