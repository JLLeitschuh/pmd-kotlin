package org.jetbrains.pmdkotlin.lang.kotlin.rule.other;

import org.jetbrains.kotlin.lexer.JetSingleValueToken;
import org.jetbrains.kotlin.lexer.JetTokens
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.types.expressions.SenselessComparisonChecker
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinASTNodeAdapter
import org.jetbrains.pmdkotlin.lang.kotlin.rule.AbstractKotlinRule;

public class SimplifyNegatedBinaryExpressionRule: AbstractKotlinRule() {
    public override fun visitPrefixExpressionPMD(expression: JetPrefixExpression, data: Any?): Any? {
        if (isApplicable(expression)) {
            addViolation(savedData, expression.getCopyableUserData(KotlinASTNodeAdapter.OUTER_NODE_KEY))
        }

        return super.visitPrefixExpressionPMD(expression, data)
    }

    private fun isApplicable(node: JetPrefixExpression): Boolean {
        if (node.getOperationToken() != JetTokens.EXCL) return false

        val expression = JetPsiUtil.deparenthesize(node.getBaseExpression()) as? JetOperationExpression ?: return false
        when (expression) {
            is JetIsExpression -> { if (expression.getTypeReference() == null) return false }
            is JetBinaryExpression -> { if (expression.getLeft() == null || expression.getRight() == null) return false }
            else -> return false
        }

        val operation = expression.getOperationReference().getReferencedNameElementType() as? JetSingleValueToken ?: return false
        val negatedOperation = negate(operation) ?: return false

        return true
    }

    private fun negate(operand: JetSingleValueToken): JetSingleValueToken? = when (operand) {
        JetTokens.IN_KEYWORD -> JetTokens.NOT_IN
        JetTokens.NOT_IN -> JetTokens.IN_KEYWORD

        JetTokens.IS_KEYWORD -> JetTokens.NOT_IS
        JetTokens.NOT_IS -> JetTokens.IS_KEYWORD

        JetTokens.EQEQ -> JetTokens.EXCLEQ
        JetTokens.EXCLEQ -> JetTokens.EQEQ

        JetTokens.LT -> JetTokens.GTEQ
        JetTokens.GTEQ -> JetTokens.LT

        JetTokens.GT -> JetTokens.LTEQ
        JetTokens.LTEQ -> JetTokens.GT

        else -> null
    }
}
