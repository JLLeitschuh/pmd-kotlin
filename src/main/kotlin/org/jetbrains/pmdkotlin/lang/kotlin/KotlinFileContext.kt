package org.jetbrains.pmdkotlin.lang.kotlin

import com.intellij.lang.Language
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.Disposer
import com.intellij.openapi.util.TextRange
import com.intellij.openapi.vfs.local.CoreLocalFileSystem
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.PsiManager
import org.apache.commons.io.IOUtils
import org.jetbrains.kotlin.cli.jvm.compiler.EnvironmentConfigFiles
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.diagnostics.DiagnosticUtils
import org.jetbrains.kotlin.parsing.KotlinParserDefinition
import java.io.IOException
import java.io.Reader

class KotlinFileContext(internal var filename: String, private val sourceCode: Reader) {

    internal val psiFile: PsiFile

    private val document: Document


    init {

        if (!filename.isEmpty() && filename != "n/a") {
            val virtualFile = CoreLocalFileSystem().findFileByPath(filename)
            val provider = virtualFile?.let { manager.findViewProvider(it) }
            psiFile = provider?.let { parserDefinition.createFile(it) } as PsiFile
        } else {
            val fileFactory = PsiFileFactory.getInstance(project)
            psiFile = fileFactory.createFileFromText("Xmlcode.kt", Language.findLanguageByID(KotlinLanguageModule.TERSE_NAME)!!, sourceCodeToString())
        }

        document = psiFile.viewProvider.document!!
    }

    fun getSourceCode(): CharSequence {
        return document.immutableCharSequence
    }

    fun getBeginLine(range: TextRange): Int {
        return findLineAndColumn(range.startOffset).line
    }

    fun getBeginColumn(range: TextRange): Int {
        return findLineAndColumn(range.startOffset).column
    }

    fun getEndLine(range: TextRange): Int {
        return findLineAndColumn(range.endOffset).line
    }

    fun getEndColumn(range: TextRange): Int {
        return findLineAndColumn(range.endOffset).column
    }

    private fun sourceCodeToString(): String {
        try {
            return IOUtils.toString(sourceCode)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }

    }

    private fun findLineAndColumn(offset: Int): DiagnosticUtils.LineAndColumn = DiagnosticUtils.offsetToLineAndColumn(document, offset)

    companion object {

        private val configuration = CompilerConfiguration()
        private val rootDisposable = Disposer.newDisposable()
        private val environment = KotlinCoreEnvironment.createForProduction(rootDisposable, configuration, EnvironmentConfigFiles.JVM_CONFIG_FILES)

        internal val project = environment.project
        private val manager = PsiManager.getInstance(project)
        internal val parserDefinition = KotlinParserDefinition.instance
    }
}
