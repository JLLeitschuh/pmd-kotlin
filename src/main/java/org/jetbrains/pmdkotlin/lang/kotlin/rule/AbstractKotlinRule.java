package org.jetbrains.pmdkotlin.lang.kotlin.rule;

import net.sourceforge.pmd.RuleContext;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.rule.AbstractRule;
import net.sourceforge.pmd.lang.rule.ImmutableLanguage;
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinParserVisitor;

import java.util.List;

public class AbstractKotlinRule extends AbstractRule implements KotlinParserVisitor, ImmutableLanguage{
    @Override
    public void apply(List<? extends Node> nodes, RuleContext ctx) {
        return;
    }
}
