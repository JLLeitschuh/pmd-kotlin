package org.jetbrains.pmdkotlin.lang.kotlin.ast

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementVisitor
import org.jetbrains.kotlin.psi.*

public interface KotlinParserVisitor {

    companion object {
        val INNER_VISITOR = object: JetVisitor<Any?, Any?>() {
            public override fun visitElement(element: PsiElement) {
                System.out.println("Visit element")
                element.acceptChildren(this);
            }
        }
    }

    public fun visitJetElement(element: JetElement, data: Any?): Any? {
        INNER_VISITOR.visitElement(element)
        return null
    }

    public fun visitDeclaration(dcl: JetDeclaration, data: Any?): Any? {
        return visitExpression(dcl, data)
    }

    public fun visitClass(klass: JetClass, data: Any?): Any? {
        return visitNamedDeclaration(klass, data)
    }

    public fun visitSecondaryConstructor(constructor: JetSecondaryConstructor, data: Any?): Any? {
        return visitDeclaration(constructor, data)
    }

    public fun visitPrimaryConstructor(constructor: JetPrimaryConstructor, data: Any?): Any? {
        return visitDeclaration(constructor, data)
    }

    public fun visitNamedFunction(function: JetNamedFunction, data: Any?): Any? {
        return visitNamedDeclaration(function, data)
    }

    public fun visitProperty(property: JetProperty, data: Any?): Any? {
        return visitNamedDeclaration(property, data)
    }

    public fun visitMultiDeclaration(multiDeclaration: JetMultiDeclaration, data: Any?): Any? {
        return visitDeclaration(multiDeclaration, data)
    }

    public fun visitMultiDeclarationEntry(multiDeclarationEntry: JetMultiDeclarationEntry, data: Any?): Any? {
        return visitNamedDeclaration(multiDeclarationEntry, data)
    }

    public fun visitTypedef(typedef: JetTypedef, data: Any?): Any? {
        return visitNamedDeclaration(typedef, data)
    }

    public fun visitJetFile(file: JetFile, data: Any?): Any? {
        INNER_VISITOR.visitFile(file)
        return null
    }

    public fun visitScript(script: JetScript, data: Any?): Any? {
        return visitDeclaration(script, data)
    }

    public fun visitImportDirective(importDirective: JetImportDirective, data: Any?): Any? {
        return visitJetElement(importDirective, data)
    }

    public fun visitImportList(importList: JetImportList, data: Any?): Any? {
        return visitJetElement(importList, data)
    }

    public fun visitFileAnnotationList(fileAnnotationList: JetFileAnnotationList, data: Any?): Any? {
        return visitJetElement(fileAnnotationList, data)
    }

    public fun visitClassBody(classBody: JetClassBody, data: Any?): Any? {
        return visitJetElement(classBody, data)
    }

    public fun visitModifierList(list: JetModifierList, data: Any?): Any? {
        return visitJetElement(list, data)
    }

    public fun visitAnnotation(annotation: JetAnnotation, data: Any?): Any? {
        return visitJetElement(annotation, data)
    }

    public fun visitAnnotationEntry(annotationEntry: JetAnnotationEntry, data: Any?): Any? {
        return visitJetElement(annotationEntry, data)
    }

    public fun visitConstructorCalleeExpression(constructorCalleeExpression: JetConstructorCalleeExpression, data: Any?): Any? {
        return visitJetElement(constructorCalleeExpression, data)
    }

    public fun visitTypeParameterList(list: JetTypeParameterList, data: Any?): Any? {
        return visitJetElement(list, data)
    }

    public fun visitTypeParameter(parameter: JetTypeParameter, data: Any?): Any? {
        return visitNamedDeclaration(parameter, data)
    }

    public fun visitEnumEntry(enumEntry: JetEnumEntry, data: Any?): Any? {
        return visitClass(enumEntry, data)
    }

    public fun visitParameterList(list: JetParameterList, data: Any?): Any? {
        return visitJetElement(list, data)
    }

    public fun visitParameter(parameter: JetParameter, data: Any?): Any? {
        return visitNamedDeclaration(parameter, data)
    }

    public fun visitDelegationSpecifierList(list: JetDelegationSpecifierList, data: Any?): Any? {
        return visitJetElement(list, data)
    }

    public fun visitDelegationSpecifier(specifier: JetDelegationSpecifier, data: Any?): Any? {
        return visitJetElement(specifier, data)
    }

    public fun visitDelegationByExpressionSpecifier(specifier: JetDelegatorByExpressionSpecifier, data: Any?): Any? {
        return visitDelegationSpecifier(specifier, data)
    }

    public fun visitDelegationToSuperCallSpecifier(call: JetDelegatorToSuperCall, data: Any?): Any? {
        return visitDelegationSpecifier(call, data)
    }

    public fun visitDelegationToSuperClassSpecifier(specifier: JetDelegatorToSuperClass, data: Any?): Any? {
        return visitDelegationSpecifier(specifier, data)
    }

    public fun visitConstructorDelegationCall(call: JetConstructorDelegationCall, data: Any?): Any? {
        return visitJetElement(call, data)
    }

    public fun visitPropertyDelegate(delegate: JetPropertyDelegate, data: Any?): Any? {
        return visitJetElement(delegate, data)
    }

    public fun visitTypeReference(typeReference: JetTypeReference, data: Any?): Any? {
        return visitJetElement(typeReference, data)
    }

    public fun visitValueArgumentList(list: JetValueArgumentList, data: Any?): Any? {
        return visitJetElement(list, data)
    }

    public fun visitArgument(argument: JetValueArgument, data: Any?): Any? {
        return visitJetElement(argument, data)
    }

    public fun visitExpression(expression: JetExpression, data: Any?): Any? {
        return visitJetElement(expression, data)
    }

    public fun visitLoopExpression(loopExpression: JetLoopExpression, data: Any?): Any? {
        return visitExpression(loopExpression, data)
    }

    public fun visitConstantExpression(expression: JetConstantExpression, data: Any?): Any? {
        return visitExpression(expression, data)
    }

    public fun visitSimpleNameExpression(expression: JetSimpleNameExpression, data: Any?): Any? {
        return visitReferenceExpression(expression, data)
    }

    public fun visitReferenceExpression(expression: JetReferenceExpression, data: Any?): Any? {
        return visitExpression(expression, data)
    }

    public fun visitLabeledExpression(expression: JetLabeledExpression, data: Any?): Any? {
        return visitExpressionWithLabel(expression, data)
    }

    public fun visitPrefixExpression(expression: JetPrefixExpression, data: Any?): Any? {
        return visitUnaryExpression(expression, data)
    }

    public fun visitPostfixExpression(expression: JetPostfixExpression, data: Any?): Any? {
        return visitUnaryExpression(expression, data)
    }

    public fun visitUnaryExpression(expression: JetUnaryExpression, data: Any?): Any? {
        return visitExpression(expression, data)
    }

    public fun visitBinaryExpression(expression: JetBinaryExpression, data: Any?): Any? {
        return visitExpression(expression, data)
    }

    public fun visitReturnExpression(expression: JetReturnExpression, data: Any?): Any? {
        return visitExpressionWithLabel(expression, data)
    }

    public fun visitExpressionWithLabel(expression: JetExpressionWithLabel, data: Any?): Any? {
        return visitExpression(expression, data)
    }

    public fun visitThrowExpression(expression: JetThrowExpression, data: Any?): Any? {
        return visitExpression(expression, data)
    }

    public fun visitBreakExpression(expression: JetBreakExpression, data: Any?): Any? {
        return visitExpressionWithLabel(expression, data)
    }

    public fun visitContinueExpression(expression: JetContinueExpression, data: Any?): Any? {
        return visitExpressionWithLabel(expression, data)
    }

    public fun visitIfExpression(expression: JetIfExpression, data: Any?): Any? {
        return visitExpression(expression, data)
    }

    public fun visitWhenExpression(expression: JetWhenExpression, data: Any?): Any? {
        return visitExpression(expression, data)
    }

    public fun visitTryExpression(expression: JetTryExpression, data: Any?): Any? {
        return visitExpression(expression, data)
    }

    public fun visitForExpression(expression: JetForExpression, data: Any?): Any? {
        return visitLoopExpression(expression, data)
    }

    public fun visitWhileExpression(expression: JetWhileExpression, data: Any?): Any? {
        return visitLoopExpression(expression, data)
    }

    public fun visitDoWhileExpression(expression: JetDoWhileExpression, data: Any?): Any? {
        return visitLoopExpression(expression, data)
    }

    public fun visitFunctionLiteralExpression(expression: JetFunctionLiteralExpression, data: Any?): Any? {
        return visitExpression(expression, data)
    }

    public fun visitAnnotatedExpression(expression: JetAnnotatedExpression, data: Any?): Any? {
        return visitExpression(expression, data)
    }

    public fun visitCallExpression(expression: JetCallExpression, data: Any?): Any? {
        return visitReferenceExpression(expression, data)
    }

    public fun visitArrayAccessExpression(expression: JetArrayAccessExpression, data: Any?): Any? {
        return visitReferenceExpression(expression, data)
    }

    public fun visitQualifiedExpression(expression: JetQualifiedExpression, data: Any?): Any? {
        return visitExpression(expression, data)
    }

    public fun visitDoubleColonExpression(expression: JetDoubleColonExpression, data: Any?): Any? {
        return visitExpression(expression, data)
    }

    public fun visitCallableReferenceExpression(expression: JetCallableReferenceExpression, data: Any?): Any? {
        return visitDoubleColonExpression(expression, data)
    }

    public fun visitClassLiteralExpression(expression: JetClassLiteralExpression, data: Any?): Any? {
        return visitDoubleColonExpression(expression, data)
    }

    public fun visitDotQualifiedExpression(expression: JetDotQualifiedExpression, data: Any?): Any? {
        return visitQualifiedExpression(expression, data)
    }

    public fun visitSafeQualifiedExpression(expression: JetSafeQualifiedExpression, data: Any?): Any? {
        return visitQualifiedExpression(expression, data)
    }

    public fun visitObjectLiteralExpression(expression: JetObjectLiteralExpression, data: Any?): Any? {
        return visitExpression(expression, data)
    }

    public fun visitRootPackageExpression(expression: JetRootPackageExpression, data: Any?): Any? {
        return visitExpression(expression, data)
    }

    public fun visitBlockExpression(expression: JetBlockExpression, data: Any?): Any? {
        return visitExpression(expression, data)
    }

    public fun visitCatchSection(catchClause: JetCatchClause, data: Any?): Any? {
        return visitJetElement(catchClause, data)
    }

    public fun visitFinallySection(finallySection: JetFinallySection, data: Any?): Any? {
        return visitJetElement(finallySection, data)
    }

    public fun visitTypeArgumentList(typeArgumentList: JetTypeArgumentList, data: Any?): Any? {
        return visitJetElement(typeArgumentList, data)
    }

    public fun visitThisExpression(expression: JetThisExpression, data: Any?): Any? {
        return visitExpressionWithLabel(expression, data)
    }

    public fun visitSuperExpression(expression: JetSuperExpression, data: Any?): Any? {
        return visitExpressionWithLabel(expression, data)
    }

    public fun visitParenthesizedExpression(expression: JetParenthesizedExpression, data: Any?): Any? {
        return visitExpression(expression, data)
    }

    public fun visitInitializerList(list: JetInitializerList, data: Any?): Any? {
        return visitJetElement(list, data)
    }

    public fun visitAnonymousInitializer(initializer: JetClassInitializer, data: Any?): Any? {
        return visitDeclaration(initializer, data)
    }

    public fun visitPropertyAccessor(accessor: JetPropertyAccessor, data: Any?): Any? {
        return visitDeclaration(accessor, data)
    }

    public fun visitTypeConstraintList(list: JetTypeConstraintList, data: Any?): Any? {
        return visitJetElement(list, data)
    }

    public fun visitTypeConstraint(constraint: JetTypeConstraint, data: Any?): Any? {
        return visitJetElement(constraint, data)
    }

    private fun visitTypeElement(type: JetTypeElement, data: Any?): Any? {
        return visitJetElement(type, data)
    }

    public fun visitUserType(type: JetUserType, data: Any?): Any? {
        return visitTypeElement(type, data)
    }

    public fun visitDynamicType(type: JetDynamicType, data: Any?): Any? {
        return visitTypeElement(type, data)
    }

    public fun visitFunctionType(type: JetFunctionType, data: Any?): Any? {
        return visitTypeElement(type, data)
    }

    public fun visitSelfType(type: JetSelfType, data: Any?): Any? {
        return visitTypeElement(type, data)
    }

    public fun visitBinaryWithTypeRHSExpression(expression: JetBinaryExpressionWithTypeRHS, data: Any?): Any? {
        return visitExpression(expression, data)
    }

    public fun visitStringTemplateExpression(expression: JetStringTemplateExpression, data: Any?): Any? {
        return visitExpression(expression, data)
    }

    public fun visitNamedDeclaration(declaration: JetNamedDeclaration, data: Any?): Any? {
        return visitDeclaration(declaration, data)
    }

    public fun visitNullableType(nullableType: JetNullableType, data: Any?): Any? {
        return visitTypeElement(nullableType, data)
    }

    public fun visitTypeProjection(typeProjection: JetTypeProjection, data: Any?): Any? {
        return visitJetElement(typeProjection, data)
    }

    public fun visitWhenEntry(jetWhenEntry: JetWhenEntry, data: Any?): Any? {
        return visitJetElement(jetWhenEntry, data)
    }

    public fun visitIsExpression(expression: JetIsExpression, data: Any?): Any? {
        return visitExpression(expression, data)
    }

    public fun visitWhenConditionIsPattern(condition: JetWhenConditionIsPattern, data: Any?): Any? {
        return visitJetElement(condition, data)
    }

    public fun visitWhenConditionInRange(condition: JetWhenConditionInRange, data: Any?): Any? {
        return visitJetElement(condition, data)
    }

    public fun visitWhenConditionWithExpression(condition: JetWhenConditionWithExpression, data: Any?): Any? {
        return visitJetElement(condition, data)
    }

    public fun visitObjectDeclaration(declaration: JetObjectDeclaration, data: Any?): Any? {
        return visitNamedDeclaration(declaration, data)
    }

    public fun visitObjectDeclarationName(declarationName: JetObjectDeclarationName, data: Any?): Any? {
        return visitExpression(declarationName, data)
    }

    public fun visitStringTemplateEntry(entry: JetStringTemplateEntry, data: Any?): Any? {
        return visitJetElement(entry, data)
    }

    public fun visitStringTemplateEntryWithExpression(entry: JetStringTemplateEntryWithExpression, data: Any?): Any? {
        return visitStringTemplateEntry(entry, data)
    }

    public fun visitBlockStringTemplateEntry(entry: JetBlockStringTemplateEntry, data: Any?): Any? {
        return visitStringTemplateEntryWithExpression(entry, data)
    }

    public fun visitSimpleNameStringTemplateEntry(entry: JetSimpleNameStringTemplateEntry, data: Any?): Any? {
        return visitStringTemplateEntryWithExpression(entry, data)
    }

    public fun visitLiteralStringTemplateEntry(entry: JetLiteralStringTemplateEntry, data: Any?): Any? {
        return visitStringTemplateEntry(entry, data)
    }

    public fun visitEscapeStringTemplateEntry(entry: JetEscapeStringTemplateEntry, data: Any?): Any? {
        return visitStringTemplateEntry(entry, data)
    }

    public fun visitPackageDirective(directive: JetPackageDirective, data: Any?): Any? {
        return visitExpression(directive, data)
    }
}
