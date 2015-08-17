package org.jetbrains.pmdkotlin.lang.kotlin.ast

import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.*

public interface KotlinParserVisitor {
    public fun toJetVisitor(): JetVisitor<Any?, Any?> {
        return object: JetVisitor<Any?, Any?>() {
            public override fun visitElement(element: PsiElement) {
                visitElementPMD(element)
            }

            public override fun visitJetElement(element: JetElement, data: Any?): Any? {
                visitJetElementPMD(element, data)
                return data
            }

            public override fun visitDeclaration(dcl: JetDeclaration, data: Any?): Any? {
                return visitDeclarationPMD(dcl, data)
            }

            public override fun visitClass(klass: JetClass, data: Any?): Any? {
                return visitClassPMD(klass, data)
            }

            public override fun visitSecondaryConstructor(constructor: JetSecondaryConstructor, data: Any?): Any? {
                return visitSecondaryConstructorPMD(constructor, data)
            }

            public override fun visitPrimaryConstructor(constructor: JetPrimaryConstructor, data: Any?): Any? {
                return visitPrimaryConstructorPMD(constructor, data)
            }

            public override fun visitNamedFunction(function: JetNamedFunction, data: Any?): Any? {
                return visitNamedFunctionPMD(function, data)
            }

            public override fun visitProperty(property: JetProperty, data: Any?): Any? {
                return visitPropertyPMD(property, data)
            }

            public override fun visitMultiDeclaration(multiDeclaration: JetMultiDeclaration, data: Any?): Any? {
                return visitMultiDeclarationPMD(multiDeclaration, data)
            }

            public override fun visitMultiDeclarationEntry(multiDeclarationEntry: JetMultiDeclarationEntry, data: Any?): Any? {
                return visitMultiDeclarationEntryPMD(multiDeclarationEntry, data)
            }

            public override fun visitTypedef(typedef: JetTypedef, data: Any?): Any? {
                return visitTypedefPMD(typedef, data)
            }

            public override fun visitJetFile(file: JetFile, data: Any?): Any? {
                visitFile(file)
                return data
            }

            public override fun visitScript(script: JetScript, data: Any?): Any? {
                return visitScriptPMD(script, data)
            }

            public override fun visitImportDirective(importDirective: JetImportDirective, data: Any?): Any? {
                return visitImportDirectivePMD(importDirective, data)
            }

            public override fun visitImportList(importList: JetImportList, data: Any?): Any? {
                return visitImportListPMD(importList, data)
            }

            public override fun visitFileAnnotationList(fileAnnotationList: JetFileAnnotationList, data: Any?): Any? {
                return visitFileAnnotationListPMD(fileAnnotationList, data)
            }

            public override fun visitClassBody(classBody: JetClassBody, data: Any?): Any? {
                return visitClassBodyPMD(classBody, data)
            }

            public override fun visitModifierList(list: JetModifierList, data: Any?): Any? {
                return visitModifierListPMD(list, data)
            }

            public override fun visitAnnotation(annotation: JetAnnotation, data: Any?): Any? {
                return visitAnnotationPMD(annotation, data)
            }

            public override fun visitAnnotationEntry(annotationEntry: JetAnnotationEntry, data: Any?): Any? {
                return visitAnnotationEntryPMD(annotationEntry, data)
            }

            public override fun visitConstructorCalleeExpression(constructorCalleeExpression: JetConstructorCalleeExpression, data: Any?): Any? {
                return visitConstructorCalleeExpressionPMD(constructorCalleeExpression, data)
            }

            public override fun visitTypeParameterList(list: JetTypeParameterList, data: Any?): Any? {
                return visitTypeParameterListPMD(list, data)
            }

            public override fun visitTypeParameter(parameter: JetTypeParameter, data: Any?): Any? {
                return visitTypeParameterPMD(parameter, data)
            }

            public override fun visitEnumEntry(enumEntry: JetEnumEntry, data: Any?): Any? {
                return visitEnumEntryPMD(enumEntry, data)
            }

            public override fun visitParameterList(list: JetParameterList, data: Any?): Any? {
                return visitParameterListPMD(list, data)
            }

            public override fun visitParameter(parameter: JetParameter, data: Any?): Any? {
                return visitParameterPMD(parameter, data)
            }

            public override fun visitDelegationSpecifierList(list: JetDelegationSpecifierList, data: Any?): Any? {
                return visitDelegationSpecifierListPMD(list, data)
            }

            public override fun visitDelegationSpecifier(specifier: JetDelegationSpecifier, data: Any?): Any? {
                return visitDelegationSpecifierPMD(specifier, data)
            }

            public override fun visitDelegationByExpressionSpecifier(specifier: JetDelegatorByExpressionSpecifier, data: Any?): Any? {
                return visitDelegationByExpressionSpecifierPMD(specifier, data)
            }

            public override fun visitDelegationToSuperCallSpecifier(call: JetDelegatorToSuperCall, data: Any?): Any? {
                return visitDelegationToSuperCallSpecifierPMD(call, data)
            }

            public override fun visitDelegationToSuperClassSpecifier(specifier: JetDelegatorToSuperClass, data: Any?): Any? {
                return visitDelegationToSuperClassSpecifierPMD(specifier, data)
            }

            public override fun visitConstructorDelegationCall(call: JetConstructorDelegationCall, data: Any?): Any? {
                return visitConstructorDelegationCallPMD(call, data)
            }

            public override fun visitPropertyDelegate(delegate: JetPropertyDelegate, data: Any?): Any? {
                return visitPropertyDelegatePMD(delegate, data)
            }

            public override fun visitTypeReference(typeReference: JetTypeReference, data: Any?): Any? {
                return visitTypeReferencePMD(typeReference, data)
            }

            public override fun visitValueArgumentList(list: JetValueArgumentList, data: Any?): Any? {
                return visitValueArgumentListPMD(list, data)
            }

            public override fun visitArgument(argument: JetValueArgument, data: Any?): Any? {
                return visitArgumentPMD(argument, data)
            }

            public override fun visitExpression(expression: JetExpression, data: Any?): Any? {
                return visitExpressionPMD(expression, data)
            }

            public override fun visitLoopExpression(loopExpression: JetLoopExpression, data: Any?): Any? {
                return visitLoopExpressionPMD(loopExpression, data)
            }

            public override fun visitConstantExpression(expression: JetConstantExpression, data: Any?): Any? {
                return visitConstantExpressionPMD(expression, data)
            }

            public override fun visitSimpleNameExpression(expression: JetSimpleNameExpression, data: Any?): Any? {
                return visitSimpleNameExpressionPMD(expression, data)
            }

            public override fun visitReferenceExpression(expression: JetReferenceExpression, data: Any?): Any? {
                return visitReferenceExpressionPMD(expression, data)
            }

            public override fun visitLabeledExpression(expression: JetLabeledExpression, data: Any?): Any? {
                return visitLabeledExpressionPMD(expression, data)
            }

            public override fun visitPrefixExpression(expression: JetPrefixExpression, data: Any?): Any? {
                return visitPrefixExpressionPMD(expression, data)
            }

            public override fun visitPostfixExpression(expression: JetPostfixExpression, data: Any?): Any? {
                return visitPostfixExpressionPMD(expression, data)
            }

            public override fun visitUnaryExpression(expression: JetUnaryExpression, data: Any?): Any? {
                return visitUnaryExpressionPMD(expression, data)
            }

            public override fun visitBinaryExpression(expression: JetBinaryExpression, data: Any?): Any? {
                return visitBinaryExpressionPMD(expression, data)
            }

            public override fun visitReturnExpression(expression: JetReturnExpression, data: Any?): Any? {
                return visitReturnExpressionPMD(expression, data)
            }

            public override fun visitExpressionWithLabel(expression: JetExpressionWithLabel, data: Any?): Any? {
                return visitExpressionWithLabelPMD(expression, data)
            }

            public override fun visitThrowExpression(expression: JetThrowExpression, data: Any?): Any? {
                return visitThrowExpressionPMD(expression, data)
            }

            public override fun visitBreakExpression(expression: JetBreakExpression, data: Any?): Any? {
                return visitBreakExpressionPMD(expression, data)
            }

            public override fun visitContinueExpression(expression: JetContinueExpression, data: Any?): Any? {
                return visitContinueExpressionPMD(expression, data)
            }

            public override fun visitIfExpression(expression: JetIfExpression, data: Any?): Any? {
                return visitIfExpressionPMD(expression, data)
            }

            public override fun visitWhenExpression(expression: JetWhenExpression, data: Any?): Any? {
                return visitWhenExpressionPMD(expression, data)
            }

            public override fun visitTryExpression(expression: JetTryExpression, data: Any?): Any? {
                return visitTryExpressionPMD(expression, data)
            }

            public override fun visitForExpression(expression: JetForExpression, data: Any?): Any? {
                return visitForExpressionPMD(expression, data)
            }

            public override fun visitWhileExpression(expression: JetWhileExpression, data: Any?): Any? {
                return visitWhileExpressionPMD(expression, data)
            }

            public override fun visitDoWhileExpression(expression: JetDoWhileExpression, data: Any?): Any? {
                return visitDoWhileExpressionPMD(expression, data)
            }

            public override fun visitFunctionLiteralExpression(expression: JetFunctionLiteralExpression, data: Any?): Any? {
                return visitFunctionLiteralExpressionPMD(expression, data)
            }

            public override fun visitAnnotatedExpression(expression: JetAnnotatedExpression, data: Any?): Any? {
                return visitAnnotatedExpressionPMD(expression, data)
            }

            public override fun visitCallExpression(expression: JetCallExpression, data: Any?): Any? {
                return visitCallExpressionPMD(expression, data)
            }

            public override fun visitArrayAccessExpression(expression: JetArrayAccessExpression, data: Any?): Any? {
                return visitArrayAccessExpressionPMD(expression, data)
            }

            public override fun visitQualifiedExpression(expression: JetQualifiedExpression, data: Any?): Any? {
                return visitQualifiedExpressionPMD(expression, data)
            }

            public override fun visitDoubleColonExpression(expression: JetDoubleColonExpression, data: Any?): Any? {
                return visitDoubleColonExpressionPMD(expression, data)
            }

            public override fun visitCallableReferenceExpression(expression: JetCallableReferenceExpression, data: Any?): Any? {
                return visitCallableReferenceExpressionPMD(expression, data)
            }

            public override fun visitClassLiteralExpression(expression: JetClassLiteralExpression, data: Any?): Any? {
                return visitClassLiteralExpressionPMD(expression, data)
            }

            public override fun visitDotQualifiedExpression(expression: JetDotQualifiedExpression, data: Any?): Any? {
                return visitDotQualifiedExpressionPMD(expression, data)
            }

            public override fun visitSafeQualifiedExpression(expression: JetSafeQualifiedExpression, data: Any?): Any? {
                return visitSafeQualifiedExpressionPMD(expression, data)
            }

            public override fun visitObjectLiteralExpression(expression: JetObjectLiteralExpression, data: Any?): Any? {
                return visitObjectLiteralExpressionPMD(expression, data)
            }

            public override fun visitRootPackageExpression(expression: JetRootPackageExpression, data: Any?): Any? {
                return visitRootPackageExpressionPMD(expression, data)
            }

            public override fun visitBlockExpression(expression: JetBlockExpression, data: Any?): Any? {
                return visitBlockExpressionPMD(expression, data)
            }

            public override fun visitCatchSection(catchClause: JetCatchClause, data: Any?): Any? {
                return visitCatchSectionPMD(catchClause, data)
            }

            public override fun visitFinallySection(finallySection: JetFinallySection, data: Any?): Any? {
                return visitFinallySectionPMD(finallySection, data)
            }

            public override fun visitTypeArgumentList(typeArgumentList: JetTypeArgumentList, data: Any?): Any? {
                return visitTypeArgumentListPMD(typeArgumentList, data)
            }

            public override fun visitThisExpression(expression: JetThisExpression, data: Any?): Any? {
                return visitThisExpressionPMD(expression, data)
            }

            public override fun visitSuperExpression(expression: JetSuperExpression, data: Any?): Any? {
                return visitSuperExpressionPMD(expression, data)
            }

            public override fun visitParenthesizedExpression(expression: JetParenthesizedExpression, data: Any?): Any? {
                return visitParenthesizedExpressionPMD(expression, data)
            }

            public override fun visitInitializerList(list: JetInitializerList, data: Any?): Any? {
                return visitInitializerListPMD(list, data)
            }

            public override fun visitAnonymousInitializer(initializer: JetClassInitializer, data: Any?): Any? {
                return visitAnonymousInitializerPMD(initializer, data)
            }

            public override fun visitPropertyAccessor(accessor: JetPropertyAccessor, data: Any?): Any? {
                return visitPropertyAccessorPMD(accessor, data)
            }

            public override fun visitTypeConstraintList(list: JetTypeConstraintList, data: Any?): Any? {
                return visitTypeConstraintListPMD(list, data)
            }

            public override fun visitTypeConstraint(constraint: JetTypeConstraint, data: Any?): Any? {
                return visitTypeConstraintPMD(constraint, data)
            }

            private fun visitTypeElement(type: JetTypeElement, data: Any?): Any? {
                return visitTypeElementPMD(type, data)
            }

            public override fun visitUserType(type: JetUserType, data: Any?): Any? {
                return visitUserTypePMD(type, data)
            }

            public override fun visitDynamicType(type: JetDynamicType, data: Any?): Any? {
                return visitDynamicTypePMD(type, data)
            }

            public override fun visitFunctionType(type: JetFunctionType, data: Any?): Any? {
                return visitFunctionTypePMD(type, data)
            }

            public override fun visitSelfType(type: JetSelfType, data: Any?): Any? {
                return visitSelfTypePMD(type, data)
            }

            public override fun visitBinaryWithTypeRHSExpression(expression: JetBinaryExpressionWithTypeRHS, data: Any?): Any? {
                return visitBinaryWithTypeRHSExpressionPMD(expression, data)
            }

            public override fun visitStringTemplateExpression(expression: JetStringTemplateExpression, data: Any?): Any? {
                return visitStringTemplateExpressionPMD(expression, data)
            }

            public override fun visitNamedDeclaration(declaration: JetNamedDeclaration, data: Any?): Any? {
                return visitNamedDeclarationPMD(declaration, data)
            }

            public override fun visitNullableType(nullableType: JetNullableType, data: Any?): Any? {
                return visitNullableTypePMD(nullableType, data)
            }

            public override fun visitTypeProjection(typeProjection: JetTypeProjection, data: Any?): Any? {
                return visitTypeProjectionPMD(typeProjection, data)
            }

            public override fun visitWhenEntry(jetWhenEntry: JetWhenEntry, data: Any?): Any? {
                return visitWhenEntryPMD(jetWhenEntry, data)
            }

            public override fun visitIsExpression(expression: JetIsExpression, data: Any?): Any? {
                return visitIsExpressionPMD(expression, data)
            }

            public override fun visitWhenConditionIsPattern(condition: JetWhenConditionIsPattern, data: Any?): Any? {
                return visitWhenConditionIsPatternPMD(condition, data)
            }

            public override fun visitWhenConditionInRange(condition: JetWhenConditionInRange, data: Any?): Any? {
                return visitWhenConditionInRangePMD(condition, data)
            }

            public override fun visitWhenConditionWithExpression(condition: JetWhenConditionWithExpression, data: Any?): Any? {
                return visitWhenConditionWithExpressionPMD(condition, data)
            }

            public override fun visitObjectDeclaration(declaration: JetObjectDeclaration, data: Any?): Any? {
                return visitObjectDeclarationPMD(declaration, data)
            }

            public override fun visitObjectDeclarationName(declarationName: JetObjectDeclarationName, data: Any?): Any? {
                return visitObjectDeclarationNamePMD(declarationName, data)
            }

            public override fun visitStringTemplateEntry(entry: JetStringTemplateEntry, data: Any?): Any? {
                return visitStringTemplateEntryPMD(entry, data)
            }

            public override fun visitStringTemplateEntryWithExpression(entry: JetStringTemplateEntryWithExpression, data: Any?): Any? {
                return visitStringTemplateEntryWithExpressionPMD(entry, data)
            }

            public override fun visitBlockStringTemplateEntry(entry: JetBlockStringTemplateEntry, data: Any?): Any? {
                return visitBlockStringTemplateEntryPMD(entry, data)
            }

            public override fun visitSimpleNameStringTemplateEntry(entry: JetSimpleNameStringTemplateEntry, data: Any?): Any? {
                return visitSimpleNameStringTemplateEntryPMD(entry, data)
            }

            public override fun visitLiteralStringTemplateEntry(entry: JetLiteralStringTemplateEntry, data: Any?): Any? {
                return visitLiteralStringTemplateEntryPMD(entry, data)
            }

            public override fun visitEscapeStringTemplateEntry(entry: JetEscapeStringTemplateEntry, data: Any?): Any? {
                return visitEscapeStringTemplateEntryPMD(entry, data)
            }

            public override fun visitPackageDirective(directive: JetPackageDirective, data: Any?): Any? {
                return visitPackageDirectivePMD(directive, data)
            }
        }
    }

    public fun visitElementPMD(element: PsiElement) {
//        System.err.println("VisitElementPMD")
        (element.getCopyableUserData(KotlinASTNodeAdapter.OUTER_NODE_KEY) as KotlinASTNodeAdapter).childrenAccept(this, null);
        //element.acceptChildren(toJetVisitor())
    }

    public fun visitJetElementPMD(element: JetElement, data: Any?): Any? {
        visitElementPMD(element)
        return data
    }

    public fun visitDeclarationPMD(dcl: JetDeclaration, data: Any?): Any? {
        return visitExpressionPMD(dcl, data)
    }

    public fun visitClassPMD(klass: JetClass, data: Any?): Any? {
        return visitNamedDeclarationPMD(klass, data)
    }

    public fun visitSecondaryConstructorPMD(constructor: JetSecondaryConstructor, data: Any?): Any? {
        return visitDeclarationPMD(constructor, data)
    }

    public fun visitPrimaryConstructorPMD(constructor: JetPrimaryConstructor, data: Any?): Any? {
        return visitDeclarationPMD(constructor, data)
    }

    public fun visitNamedFunctionPMD(function: JetNamedFunction, data: Any?): Any? {
        return visitNamedDeclarationPMD(function, data)
    }

    public fun visitPropertyPMD(property: JetProperty, data: Any?): Any? {
        return visitNamedDeclarationPMD(property, data)
    }

    public fun visitMultiDeclarationPMD(multiDeclaration: JetMultiDeclaration, data: Any?): Any? {
        return visitDeclarationPMD(multiDeclaration, data)
    }

    public fun visitMultiDeclarationEntryPMD(multiDeclarationEntry: JetMultiDeclarationEntry, data: Any?): Any? {
        return visitNamedDeclarationPMD(multiDeclarationEntry, data)
    }

    public fun visitTypedefPMD(typedef: JetTypedef, data: Any?): Any? {
        return visitNamedDeclarationPMD(typedef, data)
    }

    public fun visitJetFilePMD(file: JetFile, data: Any?): Any? {
        toJetVisitor().visitFile(file)
        return null
    }

    public fun visitScriptPMD(script: JetScript, data: Any?): Any? {
        return visitDeclarationPMD(script, data)
    }

    public fun visitImportDirectivePMD(importDirective: JetImportDirective, data: Any?): Any? {
        return visitJetElementPMD(importDirective, data)
    }

    public fun visitImportListPMD(importList: JetImportList, data: Any?): Any? {
        return visitJetElementPMD(importList, data)
    }

    public fun visitFileAnnotationListPMD(fileAnnotationList: JetFileAnnotationList, data: Any?): Any? {
        return visitJetElementPMD(fileAnnotationList, data)
    }

    public fun visitClassBodyPMD(classBody: JetClassBody, data: Any?): Any? {
        return visitJetElementPMD(classBody, data)
    }

    public fun visitModifierListPMD(list: JetModifierList, data: Any?): Any? {
        return visitJetElementPMD(list, data)
    }

    public fun visitAnnotationPMD(annotation: JetAnnotation, data: Any?): Any? {
        return visitJetElementPMD(annotation, data)
    }

    public fun visitAnnotationEntryPMD(annotationEntry: JetAnnotationEntry, data: Any?): Any? {
        return visitJetElementPMD(annotationEntry, data)
    }

    public fun visitConstructorCalleeExpressionPMD(constructorCalleeExpression: JetConstructorCalleeExpression, data: Any?): Any? {
        return visitJetElementPMD(constructorCalleeExpression, data)
    }

    public fun visitTypeParameterListPMD(list: JetTypeParameterList, data: Any?): Any? {
        return visitJetElementPMD(list, data)
    }

    public fun visitTypeParameterPMD(parameter: JetTypeParameter, data: Any?): Any? {
        return visitNamedDeclarationPMD(parameter, data)
    }

    public fun visitEnumEntryPMD(enumEntry: JetEnumEntry, data: Any?): Any? {
        return visitClassPMD(enumEntry, data)
    }

    public fun visitParameterListPMD(list: JetParameterList, data: Any?): Any? {
        return visitJetElementPMD(list, data)
    }

    public fun visitParameterPMD(parameter: JetParameter, data: Any?): Any? {
        return visitNamedDeclarationPMD(parameter, data)
    }

    public fun visitDelegationSpecifierListPMD(list: JetDelegationSpecifierList, data: Any?): Any? {
        return visitJetElementPMD(list, data)
    }

    public fun visitDelegationSpecifierPMD(specifier: JetDelegationSpecifier, data: Any?): Any? {
        return visitJetElementPMD(specifier, data)
    }

    public fun visitDelegationByExpressionSpecifierPMD(specifier: JetDelegatorByExpressionSpecifier, data: Any?): Any? {
        return visitDelegationSpecifierPMD(specifier, data)
    }

    public fun visitDelegationToSuperCallSpecifierPMD(call: JetDelegatorToSuperCall, data: Any?): Any? {
        return visitDelegationSpecifierPMD(call, data)
    }

    public fun visitDelegationToSuperClassSpecifierPMD(specifier: JetDelegatorToSuperClass, data: Any?): Any? {
        return visitDelegationSpecifierPMD(specifier, data)
    }

    public fun visitConstructorDelegationCallPMD(call: JetConstructorDelegationCall, data: Any?): Any? {
        return visitJetElementPMD(call, data)
    }

    public fun visitPropertyDelegatePMD(delegate: JetPropertyDelegate, data: Any?): Any? {
        return visitJetElementPMD(delegate, data)
    }

    public fun visitTypeReferencePMD(typeReference: JetTypeReference, data: Any?): Any? {
        return visitJetElementPMD(typeReference, data)
    }

    public fun visitValueArgumentListPMD(list: JetValueArgumentList, data: Any?): Any? {
        return visitJetElementPMD(list, data)
    }

    public fun visitArgumentPMD(argument: JetValueArgument, data: Any?): Any? {
        return visitJetElementPMD(argument, data)
    }

    public fun visitExpressionPMD(expression: JetExpression, data: Any?): Any? {
        return visitJetElementPMD(expression, data)
    }

    public fun visitLoopExpressionPMD(loopExpression: JetLoopExpression, data: Any?): Any? {
        return visitExpressionPMD(loopExpression, data)
    }

    public fun visitConstantExpressionPMD(expression: JetConstantExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    public fun visitSimpleNameExpressionPMD(expression: JetSimpleNameExpression, data: Any?): Any? {
        return visitReferenceExpressionPMD(expression, data)
    }

    public fun visitReferenceExpressionPMD(expression: JetReferenceExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    public fun visitLabeledExpressionPMD(expression: JetLabeledExpression, data: Any?): Any? {
        return visitExpressionWithLabelPMD(expression, data)
    }

    public fun visitPrefixExpressionPMD(expression: JetPrefixExpression, data: Any?): Any? {
        return visitUnaryExpressionPMD(expression, data)
    }

    public fun visitPostfixExpressionPMD(expression: JetPostfixExpression, data: Any?): Any? {
        return visitUnaryExpressionPMD(expression, data)
    }

    public fun visitUnaryExpressionPMD(expression: JetUnaryExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    public fun visitBinaryExpressionPMD(expression: JetBinaryExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    public fun visitReturnExpressionPMD(expression: JetReturnExpression, data: Any?): Any? {
        return visitExpressionWithLabelPMD(expression, data)
    }

    public fun visitExpressionWithLabelPMD(expression: JetExpressionWithLabel, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    public fun visitThrowExpressionPMD(expression: JetThrowExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    public fun visitBreakExpressionPMD(expression: JetBreakExpression, data: Any?): Any? {
        return visitExpressionWithLabelPMD(expression, data)
    }

    public fun visitContinueExpressionPMD(expression: JetContinueExpression, data: Any?): Any? {
        return visitExpressionWithLabelPMD(expression, data)
    }

    public fun visitIfExpressionPMD(expression: JetIfExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    public fun visitWhenExpressionPMD(expression: JetWhenExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    public fun visitTryExpressionPMD(expression: JetTryExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    public fun visitForExpressionPMD(expression: JetForExpression, data: Any?): Any? {
        return visitLoopExpressionPMD(expression, data)
    }

    public fun visitWhileExpressionPMD(expression: JetWhileExpression, data: Any?): Any? {
        return visitLoopExpressionPMD(expression, data)
    }

    public fun visitDoWhileExpressionPMD(expression: JetDoWhileExpression, data: Any?): Any? {
        return visitLoopExpressionPMD(expression, data)
    }

    public fun visitFunctionLiteralExpressionPMD(expression: JetFunctionLiteralExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    public fun visitAnnotatedExpressionPMD(expression: JetAnnotatedExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    public fun visitCallExpressionPMD(expression: JetCallExpression, data: Any?): Any? {
        return visitReferenceExpressionPMD(expression, data)
    }

    public fun visitArrayAccessExpressionPMD(expression: JetArrayAccessExpression, data: Any?): Any? {
        return visitReferenceExpressionPMD(expression, data)
    }

    public fun visitQualifiedExpressionPMD(expression: JetQualifiedExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    public fun visitDoubleColonExpressionPMD(expression: JetDoubleColonExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    public fun visitCallableReferenceExpressionPMD(expression: JetCallableReferenceExpression, data: Any?): Any? {
        return visitDoubleColonExpressionPMD(expression, data)
    }

    public fun visitClassLiteralExpressionPMD(expression: JetClassLiteralExpression, data: Any?): Any? {
        return visitDoubleColonExpressionPMD(expression, data)
    }

    public fun visitDotQualifiedExpressionPMD(expression: JetDotQualifiedExpression, data: Any?): Any? {
        return visitQualifiedExpressionPMD(expression, data)
    }

    public fun visitSafeQualifiedExpressionPMD(expression: JetSafeQualifiedExpression, data: Any?): Any? {
        return visitQualifiedExpressionPMD(expression, data)
    }

    public fun visitObjectLiteralExpressionPMD(expression: JetObjectLiteralExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    public fun visitRootPackageExpressionPMD(expression: JetRootPackageExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    public fun visitBlockExpressionPMD(expression: JetBlockExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    public fun visitCatchSectionPMD(catchClause: JetCatchClause, data: Any?): Any? {
        return visitJetElementPMD(catchClause, data)
    }

    public fun visitFinallySectionPMD(finallySection: JetFinallySection, data: Any?): Any? {
        return visitJetElementPMD(finallySection, data)
    }

    public fun visitTypeArgumentListPMD(typeArgumentList: JetTypeArgumentList, data: Any?): Any? {
        return visitJetElementPMD(typeArgumentList, data)
    }

    public fun visitThisExpressionPMD(expression: JetThisExpression, data: Any?): Any? {
        return visitExpressionWithLabelPMD(expression, data)
    }

    public fun visitSuperExpressionPMD(expression: JetSuperExpression, data: Any?): Any? {
        return visitExpressionWithLabelPMD(expression, data)
    }

    public fun visitParenthesizedExpressionPMD(expression: JetParenthesizedExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    public fun visitInitializerListPMD(list: JetInitializerList, data: Any?): Any? {
        return visitJetElementPMD(list, data)
    }

    public fun visitAnonymousInitializerPMD(initializer: JetClassInitializer, data: Any?): Any? {
        return visitDeclarationPMD(initializer, data)
    }

    public fun visitPropertyAccessorPMD(accessor: JetPropertyAccessor, data: Any?): Any? {
        return visitDeclarationPMD(accessor, data)
    }

    public fun visitTypeConstraintListPMD(list: JetTypeConstraintList, data: Any?): Any? {
        return visitJetElementPMD(list, data)
    }

    public fun visitTypeConstraintPMD(constraint: JetTypeConstraint, data: Any?): Any? {
        return visitJetElementPMD(constraint, data)
    }

    private fun visitTypeElementPMD(type: JetTypeElement, data: Any?): Any? {
        return visitJetElementPMD(type, data)
    }

    public fun visitUserTypePMD(type: JetUserType, data: Any?): Any? {
        return visitTypeElementPMD(type, data)
    }

    public fun visitDynamicTypePMD(type: JetDynamicType, data: Any?): Any? {
        return visitTypeElementPMD(type, data)
    }

    public fun visitFunctionTypePMD(type: JetFunctionType, data: Any?): Any? {
        return visitTypeElementPMD(type, data)
    }

    public fun visitSelfTypePMD(type: JetSelfType, data: Any?): Any? {
        return visitTypeElementPMD(type, data)
    }

    public fun visitBinaryWithTypeRHSExpressionPMD(expression: JetBinaryExpressionWithTypeRHS, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    public fun visitStringTemplateExpressionPMD(expression: JetStringTemplateExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    public fun visitNamedDeclarationPMD(declaration: JetNamedDeclaration, data: Any?): Any? {
        return visitDeclarationPMD(declaration, data)
    }

    public fun visitNullableTypePMD(nullableType: JetNullableType, data: Any?): Any? {
        return visitTypeElementPMD(nullableType, data)
    }

    public fun visitTypeProjectionPMD(typeProjection: JetTypeProjection, data: Any?): Any? {
        return visitJetElementPMD(typeProjection, data)
    }

    public fun visitWhenEntryPMD(jetWhenEntry: JetWhenEntry, data: Any?): Any? {
        return visitJetElementPMD(jetWhenEntry, data)
    }

    public fun visitIsExpressionPMD(expression: JetIsExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    public fun visitWhenConditionIsPatternPMD(condition: JetWhenConditionIsPattern, data: Any?): Any? {
        return visitJetElementPMD(condition, data)
    }

    public fun visitWhenConditionInRangePMD(condition: JetWhenConditionInRange, data: Any?): Any? {
        return visitJetElementPMD(condition, data)
    }

    public fun visitWhenConditionWithExpressionPMD(condition: JetWhenConditionWithExpression, data: Any?): Any? {
        return visitJetElementPMD(condition, data)
    }

    public fun visitObjectDeclarationPMD(declaration: JetObjectDeclaration, data: Any?): Any? {
        return visitNamedDeclarationPMD(declaration, data)
    }

    public fun visitObjectDeclarationNamePMD(declarationName: JetObjectDeclarationName, data: Any?): Any? {
        return visitExpressionPMD(declarationName, data)
    }

    public fun visitStringTemplateEntryPMD(entry: JetStringTemplateEntry, data: Any?): Any? {
        return visitJetElementPMD(entry, data)
    }

    public fun visitStringTemplateEntryWithExpressionPMD(entry: JetStringTemplateEntryWithExpression, data: Any?): Any? {
        return visitStringTemplateEntryPMD(entry, data)
    }

    public fun visitBlockStringTemplateEntryPMD(entry: JetBlockStringTemplateEntry, data: Any?): Any? {
        return visitStringTemplateEntryWithExpressionPMD(entry, data)
    }

    public fun visitSimpleNameStringTemplateEntryPMD(entry: JetSimpleNameStringTemplateEntry, data: Any?): Any? {
        return visitStringTemplateEntryWithExpressionPMD(entry, data)
    }

    public fun visitLiteralStringTemplateEntryPMD(entry: JetLiteralStringTemplateEntry, data: Any?): Any? {
        return visitStringTemplateEntryPMD(entry, data)
    }

    public fun visitEscapeStringTemplateEntryPMD(entry: JetEscapeStringTemplateEntry, data: Any?): Any? {
        return visitStringTemplateEntryPMD(entry, data)
    }

    public fun visitPackageDirectivePMD(directive: JetPackageDirective, data: Any?): Any? {
        return visitExpressionPMD(directive, data)
    }
}