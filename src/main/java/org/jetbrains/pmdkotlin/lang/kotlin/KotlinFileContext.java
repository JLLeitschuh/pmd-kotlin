package org.jetbrains.pmdkotlin.lang.kotlin;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.local.CoreLocalFileSystem;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import org.apache.commons.io.IOUtils;
import org.jetbrains.kotlin.cli.jvm.compiler.EnvironmentConfigFiles;
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.diagnostics.DiagnosticUtils;
import org.jetbrains.kotlin.parsing.KotlinParserDefinition;

import java.io.IOException;
import java.io.Reader;

public class KotlinFileContext {

    private final static CompilerConfiguration configuration = new CompilerConfiguration();
    private final static Disposable rootDisposable = Disposer.newDisposable();
    private final static KotlinCoreEnvironment environment = KotlinCoreEnvironment.createForProduction(rootDisposable, configuration, EnvironmentConfigFiles.JVM_CONFIG_FILES);

    public final static Project project = environment.getProject();
    private final static PsiManager manager = PsiManager.getInstance(project);
    public final static KotlinParserDefinition parserDefinition = KotlinParserDefinition.Companion.getInstance();

    public PsiFile psiFile;
    public String filename;

    private VirtualFile virtualFile;
    private FileViewProvider provider;
    private Document document;
    private Reader sourceCode;


    public KotlinFileContext(String fileName, Reader source) {
        this.filename = fileName;
        virtualFile = new CoreLocalFileSystem().findFileByPath(fileName);
        provider = manager.findViewProvider(virtualFile);
        psiFile = parserDefinition.createFile(provider);
        document = psiFile.getViewProvider().getDocument();
        sourceCode = source;
    }

    public String sourceCodeToString() {
        try {
            return IOUtils.toString(sourceCode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getBeginLine(TextRange range) {
        return DiagnosticUtils.offsetToLineAndColumn(document, range.getStartOffset()).getLine();
    }

    public int getBeginColumn(TextRange range) {
        return DiagnosticUtils.offsetToLineAndColumn(document, range.getStartOffset()).getColumn();
    }

    public int getEndLine(TextRange range) {
        return DiagnosticUtils.offsetToLineAndColumn(document, range.getEndOffset()).getLine();
    }

    public int getEndColumn(TextRange range) {
        return DiagnosticUtils.offsetToLineAndColumn(document, range.getEndOffset()).getColumn();
    }
}
