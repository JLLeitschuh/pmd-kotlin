package org.jetbrains.pmdkotlin.lang.kotlin

import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import net.sourceforge.pmd.lang.TokenManager
import org.jetbrains.kotlin.lexer.KotlinLexer
import org.jetbrains.kotlin.psi.KtTypeElement
import org.jetbrains.pmdkotlin.cpd.KotlinToken

class KotlinTokenManager(val kotlinFileContext: KotlinFileContext) : KotlinLexer(), TokenManager {

    var theFileName = "n/a"

    init {
        val src = reduceWindowsLineEndings(this.kotlinFileContext.sourceCodeToString())
        start(src, 0, src.length, 0)
    }

    val currentToken: KotlinToken?
        get() {
            val tokenType = tokenType
            if (tokenType != null) {
                val image = bufferSequence.subSequence(tokenStart, tokenEnd).toString()
                val range = TextRange(tokenStart, tokenEnd)

                return KotlinToken(image, kotlinFileContext.filename, tokenStart, kotlinFileContext.getBeginLine(range), tokenType)
            }
            return null
        }

    override fun getNextToken(): KotlinToken? {
        advance()
        return currentToken
    }

    override fun setFileName(fileName: String) {
        theFileName = fileName
    }

    fun findElementAt(offset: Int): PsiElement? {
        return kotlinFileContext.psiFile.findElementAt(offset)
    }

    fun isTypeToken(token: KotlinToken): Boolean {
        val leaf = findElementAt(token.ofset)
        return PsiTreeUtil.getParentOfType(leaf, KtTypeElement::class.java) != null
    }

    /**
     * Workaround for double-counted line-endings on windows environments.
     * https://youtrack.jetbrains.com/issue/KT-13489
     */
    private fun reduceWindowsLineEndings(code: String): String = code.replace("\r\n", "\n")
}
