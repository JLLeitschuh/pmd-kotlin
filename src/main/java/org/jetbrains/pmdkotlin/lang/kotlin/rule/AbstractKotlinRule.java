package org.jetbrains.pmdkotlin.lang.kotlin.rule;

import com.intellij.psi.PsiElement;
import net.sourceforge.pmd.RuleContext;
import net.sourceforge.pmd.lang.LanguageRegistry;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.rule.AbstractRule;
import net.sourceforge.pmd.lang.rule.ImmutableLanguage;
import org.jetbrains.pmdkotlin.lang.kotlin.KotlinLanguageModule;
import org.jetbrains.pmdkotlin.lang.kotlin.ast.AbstractKotlinNode;
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinNodeAdapter;
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinParserVisitor;
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinParserVisitorAdapter;

import java.util.List;

public class AbstractKotlinRule extends AbstractRule implements KotlinParserVisitor, ImmutableLanguage {
    public AbstractKotlinRule() {
        super.setLanguage(LanguageRegistry.getLanguage(KotlinLanguageModule.NAME));
    }

    @Override
    public void apply(final List<? extends Node> nodes, final RuleContext ctx) {
        visitAll(nodes, ctx);
    }

    protected void visitAll(final List<? extends Node> nodes, final RuleContext ctx) {
        for (final Object element : nodes) {
            final KotlinNodeAdapter node = (KotlinNodeAdapter) element;
            visit(node.getPsi(), ctx);
        }
    }

    public Object visit(final PsiElement node, final Object data) {
        ((AbstractKotlinNode) node).childrenAccept(this, data);
        return null;
    }
}
