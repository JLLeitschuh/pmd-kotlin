package org.jlleitschuh.pmdkotlin.lang.kotlin.rule.deadcode

import org.jetbrains.kotlin.diagnostics.Errors

public class UnusedExpressionRule : AbstractUnusedExpressionRule(Errors.UNUSED_EXPRESSION)