package org.jetbrains.pmdkotlin.lang.kotlin.ast

import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.*

interface KotlinParserVisitor {
    fun toKtVisitor(): KtVisitor<Any?, Any?> {
        return object : KtVisitor<Any?, Any?>() {
            override fun visitElement(element: PsiElement) {
                visitElementPMD(element)
            }

            override fun visitKtElement(element: KtElement, data: Any?): Any? {
                visitKtElementPMD(element, data)
                return data
            }

            override fun visitDeclaration(dcl: KtDeclaration, data: Any?): Any? {
                return visitDeclarationPMD(dcl, data)
            }

            override fun visitClass(klass: KtClass, data: Any?): Any? {
                return visitClassPMD(klass, data)
            }

            override fun visitSecondaryConstructor(constructor: KtSecondaryConstructor, data: Any?): Any? {
                return visitSecondaryConstructorPMD(constructor, data)
            }

            override fun visitPrimaryConstructor(constructor: KtPrimaryConstructor, data: Any?): Any? {
                return visitPrimaryConstructorPMD(constructor, data)
            }

            override fun visitNamedFunction(function: KtNamedFunction, data: Any?): Any? {
                return visitNamedFunctionPMD(function, data)
            }

            override fun visitProperty(property: KtProperty, data: Any?): Any? {
                return visitPropertyPMD(property, data)
            }

            override fun visitDestructuringDeclaration(multiDeclaration: KtDestructuringDeclaration, data: Any?): Any? {
                return visitDeclarationPMD(multiDeclaration, data)
            }

            override fun visitDestructuringDeclarationEntry(multiDeclarationEntry: KtDestructuringDeclarationEntry, data: Any?): Any? {
                return visitNamedDeclarationPMD(multiDeclarationEntry, data)
            }

            override fun visitKtFile(file: KtFile, data: Any?): Any? {
                visitKtFilePMD(file, data)
                return data
            }

            override fun visitScript(script: KtScript, data: Any?): Any? {
                return visitScriptPMD(script, data)
            }

            override fun visitImportDirective(importDirective: KtImportDirective, data: Any?): Any? {
                return visitImportDirectivePMD(importDirective, data)
            }

            override fun visitImportList(importList: KtImportList, data: Any?): Any? {
                return visitImportListPMD(importList, data)
            }

            override fun visitFileAnnotationList(fileAnnotationList: KtFileAnnotationList, data: Any?): Any? {
                return visitFileAnnotationListPMD(fileAnnotationList, data)
            }

            override fun visitClassBody(classBody: KtClassBody, data: Any?): Any? {
                return visitClassBodyPMD(classBody, data)
            }

            override fun visitModifierList(list: KtModifierList, data: Any?): Any? {
                return visitModifierListPMD(list, data)
            }

            override fun visitAnnotation(annotation: KtAnnotation, data: Any?): Any? {
                return visitAnnotationPMD(annotation, data)
            }

            override fun visitAnnotationEntry(annotationEntry: KtAnnotationEntry, data: Any?): Any? {
                return visitAnnotationEntryPMD(annotationEntry, data)
            }

            override fun visitConstructorCalleeExpression(constructorCalleeExpression: KtConstructorCalleeExpression, data: Any?): Any? {
                return visitConstructorCalleeExpressionPMD(constructorCalleeExpression, data)
            }

            override fun visitTypeParameterList(list: KtTypeParameterList, data: Any?): Any? {
                return visitTypeParameterListPMD(list, data)
            }

            override fun visitTypeParameter(parameter: KtTypeParameter, data: Any?): Any? {
                return visitTypeParameterPMD(parameter, data)
            }

            override fun visitEnumEntry(enumEntry: KtEnumEntry, data: Any?): Any? {
                return visitEnumEntryPMD(enumEntry, data)
            }

            override fun visitParameterList(list: KtParameterList, data: Any?): Any? {
                return visitParameterListPMD(list, data)
            }

            override fun visitParameter(parameter: KtParameter, data: Any?): Any? {
                return visitParameterPMD(parameter, data)
            }


            override fun visitConstructorDelegationCall(call: KtConstructorDelegationCall, data: Any?): Any? {
                return visitConstructorDelegationCallPMD(call, data)
            }

            override fun visitPropertyDelegate(delegate: KtPropertyDelegate, data: Any?): Any? {
                return visitPropertyDelegatePMD(delegate, data)
            }

            override fun visitTypeReference(typeReference: KtTypeReference, data: Any?): Any? {
                return visitTypeReferencePMD(typeReference, data)
            }

            override fun visitValueArgumentList(list: KtValueArgumentList, data: Any?): Any? {
                return visitValueArgumentListPMD(list, data)
            }

            override fun visitArgument(argument: KtValueArgument, data: Any?): Any? {
                return visitArgumentPMD(argument, data)
            }

            override fun visitExpression(expression: KtExpression, data: Any?): Any? {
                return visitExpressionPMD(expression, data)
            }

            override fun visitLoopExpression(loopExpression: KtLoopExpression, data: Any?): Any? {
                return visitLoopExpressionPMD(loopExpression, data)
            }

            override fun visitConstantExpression(expression: KtConstantExpression, data: Any?): Any? {
                return visitConstantExpressionPMD(expression, data)
            }

            override fun visitSimpleNameExpression(expression: KtSimpleNameExpression, data: Any?): Any? {
                return visitSimpleNameExpressionPMD(expression, data)
            }

            override fun visitReferenceExpression(expression: KtReferenceExpression, data: Any?): Any? {
                return visitReferenceExpressionPMD(expression, data)
            }

            override fun visitLabeledExpression(expression: KtLabeledExpression, data: Any?): Any? {
                return visitLabeledExpressionPMD(expression, data)
            }

            override fun visitPrefixExpression(expression: KtPrefixExpression, data: Any?): Any? {
                return visitPrefixExpressionPMD(expression, data)
            }

            override fun visitPostfixExpression(expression: KtPostfixExpression, data: Any?): Any? {
                return visitPostfixExpressionPMD(expression, data)
            }

            override fun visitUnaryExpression(expression: KtUnaryExpression, data: Any?): Any? {
                return visitUnaryExpressionPMD(expression, data)
            }

            override fun visitBinaryExpression(expression: KtBinaryExpression, data: Any?): Any? {
                return visitBinaryExpressionPMD(expression, data)
            }

            override fun visitReturnExpression(expression: KtReturnExpression, data: Any?): Any? {
                return visitReturnExpressionPMD(expression, data)
            }

            override fun visitExpressionWithLabel(expression: KtExpressionWithLabel, data: Any?): Any? {
                return visitExpressionWithLabelPMD(expression, data)
            }

            override fun visitThrowExpression(expression: KtThrowExpression, data: Any?): Any? {
                return visitThrowExpressionPMD(expression, data)
            }

            override fun visitBreakExpression(expression: KtBreakExpression, data: Any?): Any? {
                return visitBreakExpressionPMD(expression, data)
            }

            override fun visitContinueExpression(expression: KtContinueExpression, data: Any?): Any? {
                return visitContinueExpressionPMD(expression, data)
            }

            override fun visitIfExpression(expression: KtIfExpression, data: Any?): Any? {
                return visitIfExpressionPMD(expression, data)
            }

            override fun visitWhenExpression(expression: KtWhenExpression, data: Any?): Any? {
                return visitWhenExpressionPMD(expression, data)
            }

            override fun visitTryExpression(expression: KtTryExpression, data: Any?): Any? {
                return visitTryExpressionPMD(expression, data)
            }

            override fun visitForExpression(expression: KtForExpression, data: Any?): Any? {
                return visitForExpressionPMD(expression, data)
            }

            override fun visitWhileExpression(expression: KtWhileExpression, data: Any?): Any? {
                return visitWhileExpressionPMD(expression, data)
            }

            override fun visitDoWhileExpression(expression: KtDoWhileExpression, data: Any?): Any? {
                return visitDoWhileExpressionPMD(expression, data)
            }

            override fun visitAnnotatedExpression(expression: KtAnnotatedExpression, data: Any?): Any? {
                return visitAnnotatedExpressionPMD(expression, data)
            }

            override fun visitCallExpression(expression: KtCallExpression, data: Any?): Any? {
                return visitCallExpressionPMD(expression, data)
            }

            override fun visitArrayAccessExpression(expression: KtArrayAccessExpression, data: Any?): Any? {
                return visitArrayAccessExpressionPMD(expression, data)
            }

            override fun visitQualifiedExpression(expression: KtQualifiedExpression, data: Any?): Any? {
                return visitQualifiedExpressionPMD(expression, data)
            }

            override fun visitDoubleColonExpression(expression: KtDoubleColonExpression, data: Any?): Any? {
                return visitDoubleColonExpressionPMD(expression, data)
            }

            override fun visitCallableReferenceExpression(expression: KtCallableReferenceExpression, data: Any?): Any? {
                return visitCallableReferenceExpressionPMD(expression, data)
            }

            override fun visitClassLiteralExpression(expression: KtClassLiteralExpression, data: Any?): Any? {
                return visitClassLiteralExpressionPMD(expression, data)
            }

            override fun visitDotQualifiedExpression(expression: KtDotQualifiedExpression, data: Any?): Any? {
                return visitDotQualifiedExpressionPMD(expression, data)
            }

            override fun visitSafeQualifiedExpression(expression: KtSafeQualifiedExpression, data: Any?): Any? {
                return visitSafeQualifiedExpressionPMD(expression, data)
            }

            override fun visitObjectLiteralExpression(expression: KtObjectLiteralExpression, data: Any?): Any? {
                return visitObjectLiteralExpressionPMD(expression, data)
            }

            override fun visitBlockExpression(expression: KtBlockExpression, data: Any?): Any? {
                return visitBlockExpressionPMD(expression, data)
            }

            override fun visitCatchSection(catchClause: KtCatchClause, data: Any?): Any? {
                return visitCatchSectionPMD(catchClause, data)
            }

            override fun visitFinallySection(finallySection: KtFinallySection, data: Any?): Any? {
                return visitFinallySectionPMD(finallySection, data)
            }

            override fun visitTypeArgumentList(typeArgumentList: KtTypeArgumentList, data: Any?): Any? {
                return visitTypeArgumentListPMD(typeArgumentList, data)
            }

            override fun visitThisExpression(expression: KtThisExpression, data: Any?): Any? {
                return visitThisExpressionPMD(expression, data)
            }

            override fun visitSuperExpression(expression: KtSuperExpression, data: Any?): Any? {
                return visitSuperExpressionPMD(expression, data)
            }

            override fun visitParenthesizedExpression(expression: KtParenthesizedExpression, data: Any?): Any? {
                return visitParenthesizedExpressionPMD(expression, data)
            }

            override fun visitInitializerList(list: KtInitializerList, data: Any?): Any? {
                return visitInitializerListPMD(list, data)
            }

            override fun visitAnonymousInitializer(initializer: KtAnonymousInitializer, data: Any?): Any? {
                return visitAnonymousInitializerPMD(initializer, data)
            }

            override fun visitPropertyAccessor(accessor: KtPropertyAccessor, data: Any?): Any? {
                return visitPropertyAccessorPMD(accessor, data)
            }

            override fun visitTypeConstraintList(list: KtTypeConstraintList, data: Any?): Any? {
                return visitTypeConstraintListPMD(list, data)
            }

            override fun visitTypeConstraint(constraint: KtTypeConstraint, data: Any?): Any? {
                return visitTypeConstraintPMD(constraint, data)
            }

            override fun visitUserType(type: KtUserType, data: Any?): Any? {
                return visitUserTypePMD(type, data)
            }

            override fun visitDynamicType(type: KtDynamicType, data: Any?): Any? {
                return visitDynamicTypePMD(type, data)
            }

            override fun visitFunctionType(type: KtFunctionType, data: Any?): Any? {
                return visitFunctionTypePMD(type, data)
            }

            override fun visitSelfType(type: KtSelfType, data: Any?): Any? {
                return visitSelfTypePMD(type, data)
            }

            override fun visitBinaryWithTypeRHSExpression(expression: KtBinaryExpressionWithTypeRHS, data: Any?): Any? {
                return visitBinaryWithTypeRHSExpressionPMD(expression, data)
            }

            override fun visitStringTemplateExpression(expression: KtStringTemplateExpression, data: Any?): Any? {
                return visitStringTemplateExpressionPMD(expression, data)
            }

            override fun visitNamedDeclaration(declaration: KtNamedDeclaration, data: Any?): Any? {
                return visitNamedDeclarationPMD(declaration, data)
            }

            override fun visitNullableType(nullableType: KtNullableType, data: Any?): Any? {
                return visitNullableTypePMD(nullableType, data)
            }

            override fun visitTypeProjection(typeProjection: KtTypeProjection, data: Any?): Any? {
                return visitTypeProjectionPMD(typeProjection, data)
            }

            override fun visitWhenEntry(KtWhenEntry: KtWhenEntry, data: Any?): Any? {
                return visitWhenEntryPMD(KtWhenEntry, data)
            }

            override fun visitIsExpression(expression: KtIsExpression, data: Any?): Any? {
                return visitIsExpressionPMD(expression, data)
            }

            override fun visitWhenConditionIsPattern(condition: KtWhenConditionIsPattern, data: Any?): Any? {
                return visitWhenConditionIsPatternPMD(condition, data)
            }

            override fun visitWhenConditionInRange(condition: KtWhenConditionInRange, data: Any?): Any? {
                return visitWhenConditionInRangePMD(condition, data)
            }

            override fun visitWhenConditionWithExpression(condition: KtWhenConditionWithExpression, data: Any?): Any? {
                return visitWhenConditionWithExpressionPMD(condition, data)
            }

            override fun visitObjectDeclaration(declaration: KtObjectDeclaration, data: Any?): Any? {
                return visitObjectDeclarationPMD(declaration, data)
            }

            override fun visitStringTemplateEntry(entry: KtStringTemplateEntry, data: Any?): Any? {
                return visitStringTemplateEntryPMD(entry, data)
            }

            override fun visitStringTemplateEntryWithExpression(entry: KtStringTemplateEntryWithExpression, data: Any?): Any? {
                return visitStringTemplateEntryWithExpressionPMD(entry, data)
            }

            override fun visitBlockStringTemplateEntry(entry: KtBlockStringTemplateEntry, data: Any?): Any? {
                return visitBlockStringTemplateEntryPMD(entry, data)
            }

            override fun visitSimpleNameStringTemplateEntry(entry: KtSimpleNameStringTemplateEntry, data: Any?): Any? {
                return visitSimpleNameStringTemplateEntryPMD(entry, data)
            }

            override fun visitLiteralStringTemplateEntry(entry: KtLiteralStringTemplateEntry, data: Any?): Any? {
                return visitLiteralStringTemplateEntryPMD(entry, data)
            }

            override fun visitEscapeStringTemplateEntry(entry: KtEscapeStringTemplateEntry, data: Any?): Any? {
                return visitEscapeStringTemplateEntryPMD(entry, data)
            }

            override fun visitPackageDirective(directive: KtPackageDirective, data: Any?): Any? {
                return visitPackageDirectivePMD(directive, data)
            }
        }
    }

    fun visitElementPMD(element: PsiElement) {
//        System.out.println("VisitElementPMD " + element.node)
        (element.getCopyableUserData(KotlinASTNodeAdapter.OUTER_NODE_KEY) as KotlinASTNodeAdapter).childrenAccept(this, null)
    }

    fun visitKtElementPMD(element: KtElement, data: Any?): Any? {
        visitElementPMD(element)
        return data
    }

    fun visitDeclarationPMD(dcl: KtDeclaration, data: Any?): Any? {
        return visitExpressionPMD(dcl, data)
    }

    fun visitClassPMD(klass: KtClass, data: Any?): Any? {
        return visitNamedDeclarationPMD(klass, data)
    }

    fun visitSecondaryConstructorPMD(constructor: KtSecondaryConstructor, data: Any?): Any? {
        return visitDeclarationPMD(constructor, data)
    }

    fun visitPrimaryConstructorPMD(constructor: KtPrimaryConstructor, data: Any?): Any? {
        return visitDeclarationPMD(constructor, data)
    }

    fun visitNamedFunctionPMD(function: KtNamedFunction, data: Any?): Any? {
        return visitNamedDeclarationPMD(function, data)
    }

    fun visitPropertyPMD(property: KtProperty, data: Any?): Any? {
        return visitNamedDeclarationPMD(property, data)
    }

    fun visitMultiDeclarationPMD(multiDeclaration: KtDestructuringDeclaration, data: Any?): Any? {
        return visitDeclarationPMD(multiDeclaration, data)
    }

    fun visitMultiDeclarationEntryPMD(multiDeclarationEntry: KtDestructuringDeclarationEntry, data: Any?): Any? {
        return visitNamedDeclarationPMD(multiDeclarationEntry, data)
    }

    fun visitKtFilePMD(file: KtFile, data: Any?): Any? {
        toKtVisitor().visitFile(file)
        return data
    }

    fun visitScriptPMD(script: KtScript, data: Any?): Any? {
        return visitDeclarationPMD(script, data)
    }

    fun visitImportDirectivePMD(importDirective: KtImportDirective, data: Any?): Any? {
        return visitKtElementPMD(importDirective, data)
    }

    fun visitImportListPMD(importList: KtImportList, data: Any?): Any? {
        return visitKtElementPMD(importList, data)
    }

    fun visitFileAnnotationListPMD(fileAnnotationList: KtFileAnnotationList, data: Any?): Any? {
        return visitKtElementPMD(fileAnnotationList, data)
    }

    fun visitClassBodyPMD(classBody: KtClassBody, data: Any?): Any? {
        return visitKtElementPMD(classBody, data)
    }

    fun visitModifierListPMD(list: KtModifierList, data: Any?): Any? {
        return visitKtElementPMD(list, data)
    }

    fun visitAnnotationPMD(annotation: KtAnnotation, data: Any?): Any? {
        return visitKtElementPMD(annotation, data)
    }

    fun visitAnnotationEntryPMD(annotationEntry: KtAnnotationEntry, data: Any?): Any? {
        return visitKtElementPMD(annotationEntry, data)
    }

    fun visitConstructorCalleeExpressionPMD(constructorCalleeExpression: KtConstructorCalleeExpression, data: Any?): Any? {
        return visitKtElementPMD(constructorCalleeExpression, data)
    }

    fun visitTypeParameterListPMD(list: KtTypeParameterList, data: Any?): Any? {
        return visitKtElementPMD(list, data)
    }

    fun visitTypeParameterPMD(parameter: KtTypeParameter, data: Any?): Any? {
        return visitNamedDeclarationPMD(parameter, data)
    }

    fun visitEnumEntryPMD(enumEntry: KtEnumEntry, data: Any?): Any? {
        return visitClassPMD(enumEntry, data)
    }

    fun visitParameterListPMD(list: KtParameterList, data: Any?): Any? {
        return visitKtElementPMD(list, data)
    }

    fun visitParameterPMD(parameter: KtParameter, data: Any?): Any? {
        return visitNamedDeclarationPMD(parameter, data)
    }

    fun visitConstructorDelegationCallPMD(call: KtConstructorDelegationCall, data: Any?): Any? {
        return visitKtElementPMD(call, data)
    }

    fun visitPropertyDelegatePMD(delegate: KtPropertyDelegate, data: Any?): Any? {
        return visitKtElementPMD(delegate, data)
    }

    fun visitTypeReferencePMD(typeReference: KtTypeReference, data: Any?): Any? {
        return visitKtElementPMD(typeReference, data)
    }

    fun visitValueArgumentListPMD(list: KtValueArgumentList, data: Any?): Any? {
        return visitKtElementPMD(list, data)
    }

    fun visitArgumentPMD(argument: KtValueArgument, data: Any?): Any? {
        return visitKtElementPMD(argument, data)
    }

    fun visitExpressionPMD(expression: KtExpression, data: Any?): Any? {
        return visitKtElementPMD(expression, data)
    }

    fun visitLoopExpressionPMD(loopExpression: KtLoopExpression, data: Any?): Any? {
        return visitExpressionPMD(loopExpression, data)
    }

    fun visitConstantExpressionPMD(expression: KtConstantExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    fun visitSimpleNameExpressionPMD(expression: KtSimpleNameExpression, data: Any?): Any? {
        return visitReferenceExpressionPMD(expression, data)
    }

    fun visitReferenceExpressionPMD(expression: KtReferenceExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    fun visitLabeledExpressionPMD(expression: KtLabeledExpression, data: Any?): Any? {
        return visitExpressionWithLabelPMD(expression, data)
    }

    fun visitPrefixExpressionPMD(expression: KtPrefixExpression, data: Any?): Any? {
        return visitUnaryExpressionPMD(expression, data)
    }

    fun visitPostfixExpressionPMD(expression: KtPostfixExpression, data: Any?): Any? {
        return visitUnaryExpressionPMD(expression, data)
    }

    fun visitUnaryExpressionPMD(expression: KtUnaryExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    fun visitBinaryExpressionPMD(expression: KtBinaryExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    fun visitReturnExpressionPMD(expression: KtReturnExpression, data: Any?): Any? {
        return visitExpressionWithLabelPMD(expression, data)
    }

    fun visitExpressionWithLabelPMD(expression: KtExpressionWithLabel, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    fun visitThrowExpressionPMD(expression: KtThrowExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    fun visitBreakExpressionPMD(expression: KtBreakExpression, data: Any?): Any? {
        return visitExpressionWithLabelPMD(expression, data)
    }

    fun visitContinueExpressionPMD(expression: KtContinueExpression, data: Any?): Any? {
        return visitExpressionWithLabelPMD(expression, data)
    }

    fun visitIfExpressionPMD(expression: KtIfExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    fun visitWhenExpressionPMD(expression: KtWhenExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    fun visitTryExpressionPMD(expression: KtTryExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    fun visitForExpressionPMD(expression: KtForExpression, data: Any?): Any? {
        return visitLoopExpressionPMD(expression, data)
    }

    fun visitWhileExpressionPMD(expression: KtWhileExpression, data: Any?): Any? {
        return visitLoopExpressionPMD(expression, data)
    }

    fun visitDoWhileExpressionPMD(expression: KtDoWhileExpression, data: Any?): Any? {
        return visitLoopExpressionPMD(expression, data)
    }

    fun visitAnnotatedExpressionPMD(expression: KtAnnotatedExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    fun visitCallExpressionPMD(expression: KtCallExpression, data: Any?): Any? {
        return visitReferenceExpressionPMD(expression, data)
    }

    fun visitArrayAccessExpressionPMD(expression: KtArrayAccessExpression, data: Any?): Any? {
        return visitReferenceExpressionPMD(expression, data)
    }

    fun visitQualifiedExpressionPMD(expression: KtQualifiedExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    fun visitDoubleColonExpressionPMD(expression: KtDoubleColonExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    fun visitCallableReferenceExpressionPMD(expression: KtCallableReferenceExpression, data: Any?): Any? {
        return visitDoubleColonExpressionPMD(expression, data)
    }

    fun visitClassLiteralExpressionPMD(expression: KtClassLiteralExpression, data: Any?): Any? {
        return visitDoubleColonExpressionPMD(expression, data)
    }

    fun visitDotQualifiedExpressionPMD(expression: KtDotQualifiedExpression, data: Any?): Any? {
        return visitQualifiedExpressionPMD(expression, data)
    }

    fun visitSafeQualifiedExpressionPMD(expression: KtSafeQualifiedExpression, data: Any?): Any? {
        return visitQualifiedExpressionPMD(expression, data)
    }

    fun visitObjectLiteralExpressionPMD(expression: KtObjectLiteralExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    fun visitBlockExpressionPMD(expression: KtBlockExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    fun visitCatchSectionPMD(catchClause: KtCatchClause, data: Any?): Any? {
        return visitKtElementPMD(catchClause, data)
    }

    fun visitFinallySectionPMD(finallySection: KtFinallySection, data: Any?): Any? {
        return visitKtElementPMD(finallySection, data)
    }

    fun visitTypeArgumentListPMD(typeArgumentList: KtTypeArgumentList, data: Any?): Any? {
        return visitKtElementPMD(typeArgumentList, data)
    }

    fun visitThisExpressionPMD(expression: KtThisExpression, data: Any?): Any? {
        return visitExpressionWithLabelPMD(expression, data)
    }

    fun visitSuperExpressionPMD(expression: KtSuperExpression, data: Any?): Any? {
        return visitExpressionWithLabelPMD(expression, data)
    }

    fun visitParenthesizedExpressionPMD(expression: KtParenthesizedExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    fun visitInitializerListPMD(list: KtInitializerList, data: Any?): Any? {
        return visitKtElementPMD(list, data)
    }

    fun visitAnonymousInitializerPMD(initializer: KtAnonymousInitializer, data: Any?): Any? {
        return visitDeclarationPMD(initializer, data)
    }

    fun visitPropertyAccessorPMD(accessor: KtPropertyAccessor, data: Any?): Any? {
        return visitDeclarationPMD(accessor, data)
    }

    fun visitTypeConstraintListPMD(list: KtTypeConstraintList, data: Any?): Any? {
        return visitKtElementPMD(list, data)
    }

    fun visitTypeConstraintPMD(constraint: KtTypeConstraint, data: Any?): Any? {
        return visitKtElementPMD(constraint, data)
    }

    private fun visitTypeElementPMD(type: KtTypeElement, data: Any?): Any? {
        return visitKtElementPMD(type, data)
    }

    fun visitUserTypePMD(type: KtUserType, data: Any?): Any? {
        return visitTypeElementPMD(type, data)
    }

    fun visitDynamicTypePMD(type: KtDynamicType, data: Any?): Any? {
        return visitTypeElementPMD(type, data)
    }

    fun visitFunctionTypePMD(type: KtFunctionType, data: Any?): Any? {
        return visitTypeElementPMD(type, data)
    }

    fun visitSelfTypePMD(type: KtSelfType, data: Any?): Any? {
        return visitTypeElementPMD(type, data)
    }

    fun visitBinaryWithTypeRHSExpressionPMD(expression: KtBinaryExpressionWithTypeRHS, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    fun visitStringTemplateExpressionPMD(expression: KtStringTemplateExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    fun visitNamedDeclarationPMD(declaration: KtNamedDeclaration, data: Any?): Any? {
        return visitDeclarationPMD(declaration, data)
    }

    fun visitNullableTypePMD(nullableType: KtNullableType, data: Any?): Any? {
        return visitTypeElementPMD(nullableType, data)
    }

    fun visitTypeProjectionPMD(typeProjection: KtTypeProjection, data: Any?): Any? {
        return visitKtElementPMD(typeProjection, data)
    }

    fun visitWhenEntryPMD(KtWhenEntry: KtWhenEntry, data: Any?): Any? {
        return visitKtElementPMD(KtWhenEntry, data)
    }

    fun visitIsExpressionPMD(expression: KtIsExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    fun visitWhenConditionIsPatternPMD(condition: KtWhenConditionIsPattern, data: Any?): Any? {
        return visitKtElementPMD(condition, data)
    }

    fun visitWhenConditionInRangePMD(condition: KtWhenConditionInRange, data: Any?): Any? {
        return visitKtElementPMD(condition, data)
    }

    fun visitWhenConditionWithExpressionPMD(condition: KtWhenConditionWithExpression, data: Any?): Any? {
        return visitKtElementPMD(condition, data)
    }

    fun visitObjectDeclarationPMD(declaration: KtObjectDeclaration, data: Any?): Any? {
        return visitNamedDeclarationPMD(declaration, data)
    }

    fun visitStringTemplateEntryPMD(entry: KtStringTemplateEntry, data: Any?): Any? {
        return visitKtElementPMD(entry, data)
    }

    fun visitStringTemplateEntryWithExpressionPMD(entry: KtStringTemplateEntryWithExpression, data: Any?): Any? {
        return visitStringTemplateEntryPMD(entry, data)
    }

    fun visitBlockStringTemplateEntryPMD(entry: KtBlockStringTemplateEntry, data: Any?): Any? {
        return visitStringTemplateEntryWithExpressionPMD(entry, data)
    }

    fun visitSimpleNameStringTemplateEntryPMD(entry: KtSimpleNameStringTemplateEntry, data: Any?): Any? {
        return visitStringTemplateEntryWithExpressionPMD(entry, data)
    }

    fun visitLiteralStringTemplateEntryPMD(entry: KtLiteralStringTemplateEntry, data: Any?): Any? {
        return visitStringTemplateEntryPMD(entry, data)
    }

    fun visitEscapeStringTemplateEntryPMD(entry: KtEscapeStringTemplateEntry, data: Any?): Any? {
        return visitStringTemplateEntryPMD(entry, data)
    }

    fun visitPackageDirectivePMD(directive: KtPackageDirective, data: Any?): Any? {
        return visitElementPMD(directive)
    }
}