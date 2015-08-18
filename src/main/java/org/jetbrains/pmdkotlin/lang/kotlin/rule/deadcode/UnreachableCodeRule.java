package org.jetbrains.pmdkotlin.lang.kotlin.rule.deadcode;

import com.google.common.collect.Sets;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import net.sourceforge.pmd.lang.ast.Node;
import org.jetbrains.kotlin.cfg.JetControlFlowProcessor;
import org.jetbrains.kotlin.cfg.JetFlowInformationProvider;
import org.jetbrains.kotlin.cfg.UnreachableCode;
import org.jetbrains.kotlin.cfg.UnreachableCodeImpl;
import org.jetbrains.kotlin.cfg.pseudocode.Pseudocode;
import org.jetbrains.kotlin.cfg.pseudocode.instructions.Instruction;
import org.jetbrains.kotlin.cfg.pseudocode.instructions.JetElementInstruction;
import org.jetbrains.kotlin.cfg.pseudocode.instructions.eval.LoadUnitValueInstruction;
import org.jetbrains.kotlin.cfg.pseudocode.instructions.eval.MagicInstruction;
import org.jetbrains.kotlin.cfg.pseudocode.instructions.eval.MergeInstruction;
import org.jetbrains.kotlin.cfg.pseudocode.instructions.jumps.JumpInstruction;
import org.jetbrains.kotlin.psi.*;
import org.jetbrains.kotlin.resolve.BindingTrace;
import org.jetbrains.kotlin.resolve.BindingTraceContext;
import org.jetbrains.pmdkotlin.lang.kotlin.ast.KotlinASTNodeAdapter;
import org.jetbrains.pmdkotlin.lang.kotlin.rule.AbstractKotlinRule;

import java.lang.reflect.Method;
import java.util.Set;

import static java.lang.Boolean.TRUE;

public class UnreachableCodeRule extends AbstractKotlinRule {
    @Override
    public void visitElementPMD(PsiElement node) {
        KotlinASTNodeAdapter outerNode = (KotlinASTNodeAdapter) node.getCopyableUserData(KotlinASTNodeAdapter.OUTER_NODE_KEY);
        if (node.getCopyableUserData(KotlinASTNodeAdapter.UNREACHABLE_KEY)) {
            addViolation(getSavedData(), outerNode, new Object[]{outerNode.getBeginLine(), outerNode.getEndLine()});
        } else {
            if (node instanceof JetElement) {
                JetFlowInformationProvider provider = new JetFlowInformationProvider((JetElement) node, outerNode.trace);
                try {
                    Method m = provider.getClass().getDeclaredMethod("collectUnreachableCode");
                    m.setAccessible(true);
                    UnreachableCode unreachableCode = (UnreachableCode) m.invoke(provider);
                    for (JetElement element : unreachableCode.getElements()) {
                        element.putCopyableUserData(KotlinASTNodeAdapter.UNREACHABLE_KEY, Boolean.TRUE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        super.visitElementPMD(node);
    }
}
