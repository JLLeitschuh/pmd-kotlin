package org.jetbrains.pmdkotlin.lang.kotlin;

import com.intellij.lang.Language;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.local.CoreLocalFileSystem;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
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

    final static Project project = environment.getProject();
    private final static PsiManager manager = PsiManager.getInstance(project);
    final static KotlinParserDefinition parserDefinition = KotlinParserDefinition.Companion.getInstance();

    PsiFile psiFile;
    String filename;

    private Document document;
    private Reader sourceCode;


    public KotlinFileContext(String fileName, Reader source) {
        this.filename = fileName;

        if (fileName != null && !fileName.isEmpty() && !fileName.equals("n/a")) {
            VirtualFile virtualFile = new CoreLocalFileSystem().findFileByPath(fileName);
            FileViewProvider provider = manager.findViewProvider(virtualFile);
            psiFile = parserDefinition.createFile(provider);
        } else {
            PsiFileFactory fileFactory = PsiFileFactory.getInstance(project);
            psiFile = fileFactory.createFileFromText("Xmlcode.kt", Language.findLanguageByID(KotlinLanguageModule.TERSE_NAME), "<" + fileName + "/>");
        }

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
