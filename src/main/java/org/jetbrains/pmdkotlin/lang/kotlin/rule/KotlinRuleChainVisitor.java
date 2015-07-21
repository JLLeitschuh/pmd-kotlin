package org.jetbrains.pmdkotlin.lang.kotlin.rule;

import net.sourceforge.pmd.Rule;
import net.sourceforge.pmd.RuleContext;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.rule.AbstractRuleChainVisitor;

import java.util.List;

public class KotlinRuleChainVisitor extends AbstractRuleChainVisitor{
    @Override
    protected void visit(Rule rule, Node node, RuleContext ctx) {
        return;
    }

    @Override
    protected void indexNodes(List<Node> nodes, RuleContext ctx) {
        return;
    }
}
