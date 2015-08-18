package org.jetbrains.pmdkotlin.lang.kotlin.rule;

import net.sourceforge.pmd.Rule;
import net.sourceforge.pmd.RuleContext;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.rule.AbstractRuleChainVisitor;
import net.sourceforge.pmd.lang.rule.XPathRule;
import org.jetbrains.pmdkotlin.lang.kotlin.ast.AbstractKotlinNode;
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinParserVisitorAdapter;

import java.util.List;

public class KotlinRuleChainVisitor extends AbstractRuleChainVisitor{
    @Override
    protected void visit(Rule rule, Node node, RuleContext ctx) {
        if (rule instanceof KotlinParserVisitorAdapter) {
            ((AbstractKotlinNode) node).jjtAccept((KotlinParserVisitorAdapter) rule, ctx);
        } else {
            ((XPathRule)rule).evaluate(node, ctx);
        }
        return;
    }

    @Override
    protected void indexNodes(List<Node> nodes, RuleContext ctx) {
        KotlinParserVisitorAdapter visitor = new KotlinParserVisitorAdapter();

        for (int i = 0; i < nodes.size(); i++) {
            visitor.visit(nodes.get(i), ctx);
        }

    }
}
