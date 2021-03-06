package org.jlleitschuh.pmdkotlin.lang.kotlin.rule.other

import org.jetbrains.kotlin.lexer.KtSingleValueToken
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtBinaryExpression
import org.jetbrains.kotlin.psi.KtIsExpression
import org.jetbrains.kotlin.psi.KtOperationExpression
import org.jetbrains.kotlin.psi.KtPrefixExpression
import org.jetbrains.kotlin.psi.KtPsiUtil
import org.jlleitschuh.pmdkotlin.lang.kotlin.ast.KotlinASTNodeAdapter
import org.jlleitschuh.pmdkotlin.lang.kotlin.rule.AbstractKotlinRule

class SimplifyNegatedBinaryExpression : AbstractKotlinRule() {
    override fun visitPrefixExpressionPMD(expression: KtPrefixExpression, data: Any?): Any? {
        if (isApplicable(expression)) {
            addViolation(savedData, expression.getCopyableUserData(KotlinASTNodeAdapter.OUTER_NODE_KEY))
        }

        return super.visitPrefixExpressionPMD(expression, data)
    }

    private fun isApplicable(node: KtPrefixExpression): Boolean {
        if (node.operationToken != KtTokens.EXCL) return false

        val expression = KtPsiUtil.deparenthesize(node.baseExpression) as? KtOperationExpression ?: return false
        when (expression) {
            is KtIsExpression -> {
                if (expression.typeReference == null) return false
            }
            is KtBinaryExpression -> {
                if (expression.left == null || expression.right == null) return false
            }
            else -> return false
        }

        val operation = expression.operationReference.getReferencedNameElementType() as? KtSingleValueToken ?: return false
        val negatedOperation = negate(operation) ?: return false

        return true
    }

    private fun negate(operand: KtSingleValueToken): KtSingleValueToken? = when (operand) {
        KtTokens.IN_KEYWORD -> KtTokens.NOT_IN
        KtTokens.NOT_IN -> KtTokens.IN_KEYWORD

        KtTokens.IS_KEYWORD -> KtTokens.NOT_IS
        KtTokens.NOT_IS -> KtTokens.IS_KEYWORD

        KtTokens.EQEQ -> KtTokens.EXCLEQ
        KtTokens.EXCLEQ -> KtTokens.EQEQ

        KtTokens.LT -> KtTokens.GTEQ
        KtTokens.GTEQ -> KtTokens.LT

        KtTokens.GT -> KtTokens.LTEQ
        KtTokens.LTEQ -> KtTokens.GT

        else -> null
    }
}
