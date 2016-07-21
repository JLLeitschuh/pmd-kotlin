package org.jetbrains.pmdkotlin.lang.kotlin.ast

import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.*

public interface KotlinParserVisitor {
    public fun toKtVisitor(): KtVisitor<Any?, Any?> {
        return object : KtVisitor<Any?, Any?>() {
            public override fun visitElement(element: PsiElement) {
                visitElementPMD(element)
            }

            public override fun visitKtElement(element: KtElement, data: Any?): Any? {
                visitKtElementPMD(element, data)
                return data
            }

            public override fun visitDeclaration(dcl: KtDeclaration, data: Any?): Any? {
                return visitDeclarationPMD(dcl, data)
            }

            public override fun visitClass(klass: KtClass, data: Any?): Any? {
                return visitClassPMD(klass, data)
            }

            public override fun visitSecondaryConstructor(constructor: KtSecondaryConstructor, data: Any?): Any? {
                return visitSecondaryConstructorPMD(constructor, data)
            }

            public override fun visitPrimaryConstructor(constructor: KtPrimaryConstructor, data: Any?): Any? {
                return visitPrimaryConstructorPMD(constructor, data)
            }

            public override fun visitNamedFunction(function: KtNamedFunction, data: Any?): Any? {
                return visitNamedFunctionPMD(function, data)
            }

            public override fun visitProperty(property: KtProperty, data: Any?): Any? {
                return visitPropertyPMD(property, data)
            }

            override fun visitDestructuringDeclaration(multiDeclaration: KtDestructuringDeclaration, data: Any?): Any? {
                return visitDeclarationPMD(multiDeclaration, data)
            }

            override fun visitDestructuringDeclarationEntry(multiDeclarationEntry: KtDestructuringDeclarationEntry, data: Any?): Any? {
                return visitNamedDeclarationPMD(multiDeclarationEntry, data)
            }

            public override fun visitKtFile(file: KtFile, data: Any?): Any? {
                visitKtFilePMD(file, data)
                return data
            }

            public override fun visitScript(script: KtScript, data: Any?): Any? {
                return visitScriptPMD(script, data)
            }

            public override fun visitImportDirective(importDirective: KtImportDirective, data: Any?): Any? {
                return visitImportDirectivePMD(importDirective, data)
            }

            public override fun visitImportList(importList: KtImportList, data: Any?): Any? {
                return visitImportListPMD(importList, data)
            }

            public override fun visitFileAnnotationList(fileAnnotationList: KtFileAnnotationList, data: Any?): Any? {
                return visitFileAnnotationListPMD(fileAnnotationList, data)
            }

            public override fun visitClassBody(classBody: KtClassBody, data: Any?): Any? {
                return visitClassBodyPMD(classBody, data)
            }

            public override fun visitModifierList(list: KtModifierList, data: Any?): Any? {
                return visitModifierListPMD(list, data)
            }

            public override fun visitAnnotation(annotation: KtAnnotation, data: Any?): Any? {
                return visitAnnotationPMD(annotation, data)
            }

            public override fun visitAnnotationEntry(annotationEntry: KtAnnotationEntry, data: Any?): Any? {
                return visitAnnotationEntryPMD(annotationEntry, data)
            }

            public override fun visitConstructorCalleeExpression(constructorCalleeExpression: KtConstructorCalleeExpression, data: Any?): Any? {
                return visitConstructorCalleeExpressionPMD(constructorCalleeExpression, data)
            }

            public override fun visitTypeParameterList(list: KtTypeParameterList, data: Any?): Any? {
                return visitTypeParameterListPMD(list, data)
            }

            public override fun visitTypeParameter(parameter: KtTypeParameter, data: Any?): Any? {
                return visitTypeParameterPMD(parameter, data)
            }

            public override fun visitEnumEntry(enumEntry: KtEnumEntry, data: Any?): Any? {
                return visitEnumEntryPMD(enumEntry, data)
            }

            public override fun visitParameterList(list: KtParameterList, data: Any?): Any? {
                return visitParameterListPMD(list, data)
            }

            public override fun visitParameter(parameter: KtParameter, data: Any?): Any? {
                return visitParameterPMD(parameter, data)
            }


            public override fun visitConstructorDelegationCall(call: KtConstructorDelegationCall, data: Any?): Any? {
                return visitConstructorDelegationCallPMD(call, data)
            }

            public override fun visitPropertyDelegate(delegate: KtPropertyDelegate, data: Any?): Any? {
                return visitPropertyDelegatePMD(delegate, data)
            }

            public override fun visitTypeReference(typeReference: KtTypeReference, data: Any?): Any? {
                return visitTypeReferencePMD(typeReference, data)
            }

            public override fun visitValueArgumentList(list: KtValueArgumentList, data: Any?): Any? {
                return visitValueArgumentListPMD(list, data)
            }

            public override fun visitArgument(argument: KtValueArgument, data: Any?): Any? {
                return visitArgumentPMD(argument, data)
            }

            public override fun visitExpression(expression: KtExpression, data: Any?): Any? {
                return visitExpressionPMD(expression, data)
            }

            public override fun visitLoopExpression(loopExpression: KtLoopExpression, data: Any?): Any? {
                return visitLoopExpressionPMD(loopExpression, data)
            }

            public override fun visitConstantExpression(expression: KtConstantExpression, data: Any?): Any? {
                return visitConstantExpressionPMD(expression, data)
            }

            public override fun visitSimpleNameExpression(expression: KtSimpleNameExpression, data: Any?): Any? {
                return visitSimpleNameExpressionPMD(expression, data)
            }

            public override fun visitReferenceExpression(expression: KtReferenceExpression, data: Any?): Any? {
                return visitReferenceExpressionPMD(expression, data)
            }

            public override fun visitLabeledExpression(expression: KtLabeledExpression, data: Any?): Any? {
                return visitLabeledExpressionPMD(expression, data)
            }

            public override fun visitPrefixExpression(expression: KtPrefixExpression, data: Any?): Any? {
                return visitPrefixExpressionPMD(expression, data)
            }

            public override fun visitPostfixExpression(expression: KtPostfixExpression, data: Any?): Any? {
                return visitPostfixExpressionPMD(expression, data)
            }

            public override fun visitUnaryExpression(expression: KtUnaryExpression, data: Any?): Any? {
                return visitUnaryExpressionPMD(expression, data)
            }

            public override fun visitBinaryExpression(expression: KtBinaryExpression, data: Any?): Any? {
                return visitBinaryExpressionPMD(expression, data)
            }

            public override fun visitReturnExpression(expression: KtReturnExpression, data: Any?): Any? {
                return visitReturnExpressionPMD(expression, data)
            }

            public override fun visitExpressionWithLabel(expression: KtExpressionWithLabel, data: Any?): Any? {
                return visitExpressionWithLabelPMD(expression, data)
            }

            public override fun visitThrowExpression(expression: KtThrowExpression, data: Any?): Any? {
                return visitThrowExpressionPMD(expression, data)
            }

            public override fun visitBreakExpression(expression: KtBreakExpression, data: Any?): Any? {
                return visitBreakExpressionPMD(expression, data)
            }

            public override fun visitContinueExpression(expression: KtContinueExpression, data: Any?): Any? {
                return visitContinueExpressionPMD(expression, data)
            }

            public override fun visitIfExpression(expression: KtIfExpression, data: Any?): Any? {
                return visitIfExpressionPMD(expression, data)
            }

            public override fun visitWhenExpression(expression: KtWhenExpression, data: Any?): Any? {
                return visitWhenExpressionPMD(expression, data)
            }

            public override fun visitTryExpression(expression: KtTryExpression, data: Any?): Any? {
                return visitTryExpressionPMD(expression, data)
            }

            public override fun visitForExpression(expression: KtForExpression, data: Any?): Any? {
                return visitForExpressionPMD(expression, data)
            }

            public override fun visitWhileExpression(expression: KtWhileExpression, data: Any?): Any? {
                return visitWhileExpressionPMD(expression, data)
            }

            public override fun visitDoWhileExpression(expression: KtDoWhileExpression, data: Any?): Any? {
                return visitDoWhileExpressionPMD(expression, data)
            }

            public override fun visitAnnotatedExpression(expression: KtAnnotatedExpression, data: Any?): Any? {
                return visitAnnotatedExpressionPMD(expression, data)
            }

            public override fun visitCallExpression(expression: KtCallExpression, data: Any?): Any? {
                return visitCallExpressionPMD(expression, data)
            }

            public override fun visitArrayAccessExpression(expression: KtArrayAccessExpression, data: Any?): Any? {
                return visitArrayAccessExpressionPMD(expression, data)
            }

            public override fun visitQualifiedExpression(expression: KtQualifiedExpression, data: Any?): Any? {
                return visitQualifiedExpressionPMD(expression, data)
            }

            public override fun visitDoubleColonExpression(expression: KtDoubleColonExpression, data: Any?): Any? {
                return visitDoubleColonExpressionPMD(expression, data)
            }

            public override fun visitCallableReferenceExpression(expression: KtCallableReferenceExpression, data: Any?): Any? {
                return visitCallableReferenceExpressionPMD(expression, data)
            }

            public override fun visitClassLiteralExpression(expression: KtClassLiteralExpression, data: Any?): Any? {
                return visitClassLiteralExpressionPMD(expression, data)
            }

            public override fun visitDotQualifiedExpression(expression: KtDotQualifiedExpression, data: Any?): Any? {
                return visitDotQualifiedExpressionPMD(expression, data)
            }

            public override fun visitSafeQualifiedExpression(expression: KtSafeQualifiedExpression, data: Any?): Any? {
                return visitSafeQualifiedExpressionPMD(expression, data)
            }

            public override fun visitObjectLiteralExpression(expression: KtObjectLiteralExpression, data: Any?): Any? {
                return visitObjectLiteralExpressionPMD(expression, data)
            }

            public override fun visitBlockExpression(expression: KtBlockExpression, data: Any?): Any? {
                return visitBlockExpressionPMD(expression, data)
            }

            public override fun visitCatchSection(catchClause: KtCatchClause, data: Any?): Any? {
                return visitCatchSectionPMD(catchClause, data)
            }

            public override fun visitFinallySection(finallySection: KtFinallySection, data: Any?): Any? {
                return visitFinallySectionPMD(finallySection, data)
            }

            public override fun visitTypeArgumentList(typeArgumentList: KtTypeArgumentList, data: Any?): Any? {
                return visitTypeArgumentListPMD(typeArgumentList, data)
            }

            public override fun visitThisExpression(expression: KtThisExpression, data: Any?): Any? {
                return visitThisExpressionPMD(expression, data)
            }

            public override fun visitSuperExpression(expression: KtSuperExpression, data: Any?): Any? {
                return visitSuperExpressionPMD(expression, data)
            }

            public override fun visitParenthesizedExpression(expression: KtParenthesizedExpression, data: Any?): Any? {
                return visitParenthesizedExpressionPMD(expression, data)
            }

            public override fun visitInitializerList(list: KtInitializerList, data: Any?): Any? {
                return visitInitializerListPMD(list, data)
            }

            public override fun visitAnonymousInitializer(initializer: KtAnonymousInitializer, data: Any?): Any? {
                return visitAnonymousInitializerPMD(initializer, data)
            }

            public override fun visitPropertyAccessor(accessor: KtPropertyAccessor, data: Any?): Any? {
                return visitPropertyAccessorPMD(accessor, data)
            }

            public override fun visitTypeConstraintList(list: KtTypeConstraintList, data: Any?): Any? {
                return visitTypeConstraintListPMD(list, data)
            }

            public override fun visitTypeConstraint(constraint: KtTypeConstraint, data: Any?): Any? {
                return visitTypeConstraintPMD(constraint, data)
            }

            public override fun visitUserType(type: KtUserType, data: Any?): Any? {
                return visitUserTypePMD(type, data)
            }

            public override fun visitDynamicType(type: KtDynamicType, data: Any?): Any? {
                return visitDynamicTypePMD(type, data)
            }

            public override fun visitFunctionType(type: KtFunctionType, data: Any?): Any? {
                return visitFunctionTypePMD(type, data)
            }

            public override fun visitSelfType(type: KtSelfType, data: Any?): Any? {
                return visitSelfTypePMD(type, data)
            }

            public override fun visitBinaryWithTypeRHSExpression(expression: KtBinaryExpressionWithTypeRHS, data: Any?): Any? {
                return visitBinaryWithTypeRHSExpressionPMD(expression, data)
            }

            public override fun visitStringTemplateExpression(expression: KtStringTemplateExpression, data: Any?): Any? {
                return visitStringTemplateExpressionPMD(expression, data)
            }

            public override fun visitNamedDeclaration(declaration: KtNamedDeclaration, data: Any?): Any? {
                return visitNamedDeclarationPMD(declaration, data)
            }

            public override fun visitNullableType(nullableType: KtNullableType, data: Any?): Any? {
                return visitNullableTypePMD(nullableType, data)
            }

            public override fun visitTypeProjection(typeProjection: KtTypeProjection, data: Any?): Any? {
                return visitTypeProjectionPMD(typeProjection, data)
            }

            public override fun visitWhenEntry(KtWhenEntry: KtWhenEntry, data: Any?): Any? {
                return visitWhenEntryPMD(KtWhenEntry, data)
            }

            public override fun visitIsExpression(expression: KtIsExpression, data: Any?): Any? {
                return visitIsExpressionPMD(expression, data)
            }

            public override fun visitWhenConditionIsPattern(condition: KtWhenConditionIsPattern, data: Any?): Any? {
                return visitWhenConditionIsPatternPMD(condition, data)
            }

            public override fun visitWhenConditionInRange(condition: KtWhenConditionInRange, data: Any?): Any? {
                return visitWhenConditionInRangePMD(condition, data)
            }

            public override fun visitWhenConditionWithExpression(condition: KtWhenConditionWithExpression, data: Any?): Any? {
                return visitWhenConditionWithExpressionPMD(condition, data)
            }

            public override fun visitObjectDeclaration(declaration: KtObjectDeclaration, data: Any?): Any? {
                return visitObjectDeclarationPMD(declaration, data)
            }

            public override fun visitStringTemplateEntry(entry: KtStringTemplateEntry, data: Any?): Any? {
                return visitStringTemplateEntryPMD(entry, data)
            }

            public override fun visitStringTemplateEntryWithExpression(entry: KtStringTemplateEntryWithExpression, data: Any?): Any? {
                return visitStringTemplateEntryWithExpressionPMD(entry, data)
            }

            public override fun visitBlockStringTemplateEntry(entry: KtBlockStringTemplateEntry, data: Any?): Any? {
                return visitBlockStringTemplateEntryPMD(entry, data)
            }

            public override fun visitSimpleNameStringTemplateEntry(entry: KtSimpleNameStringTemplateEntry, data: Any?): Any? {
                return visitSimpleNameStringTemplateEntryPMD(entry, data)
            }

            public override fun visitLiteralStringTemplateEntry(entry: KtLiteralStringTemplateEntry, data: Any?): Any? {
                return visitLiteralStringTemplateEntryPMD(entry, data)
            }

            public override fun visitEscapeStringTemplateEntry(entry: KtEscapeStringTemplateEntry, data: Any?): Any? {
                return visitEscapeStringTemplateEntryPMD(entry, data)
            }

            public override fun visitPackageDirective(directive: KtPackageDirective, data: Any?): Any? {
                return visitPackageDirectivePMD(directive, data)
            }
        }
    }

    public fun visitElementPMD(element: PsiElement) {
//        System.err.println("VisitElementPMD")
        (element.getCopyableUserData(KotlinASTNodeAdapter.OUTER_NODE_KEY) as KotlinASTNodeAdapter).childrenAccept(this, null);
        //element.acceptChildren(toKtVisitor())
    }

    public fun visitKtElementPMD(element: KtElement, data: Any?): Any? {
        visitElementPMD(element)
        return data
    }

    public fun visitDeclarationPMD(dcl: KtDeclaration, data: Any?): Any? {
        return visitExpressionPMD(dcl, data)
    }

    public fun visitClassPMD(klass: KtClass, data: Any?): Any? {
        return visitNamedDeclarationPMD(klass, data)
    }

    public fun visitSecondaryConstructorPMD(constructor: KtSecondaryConstructor, data: Any?): Any? {
        return visitDeclarationPMD(constructor, data)
    }

    public fun visitPrimaryConstructorPMD(constructor: KtPrimaryConstructor, data: Any?): Any? {
        return visitDeclarationPMD(constructor, data)
    }

    public fun visitNamedFunctionPMD(function: KtNamedFunction, data: Any?): Any? {
        return visitNamedDeclarationPMD(function, data)
    }

    public fun visitPropertyPMD(property: KtProperty, data: Any?): Any? {
        return visitNamedDeclarationPMD(property, data)
    }

    public fun visitMultiDeclarationPMD(multiDeclaration: KtDestructuringDeclaration, data: Any?): Any? {
        return visitDeclarationPMD(multiDeclaration, data)
    }

    public fun visitMultiDeclarationEntryPMD(multiDeclarationEntry: KtDestructuringDeclarationEntry, data: Any?): Any? {
        return visitNamedDeclarationPMD(multiDeclarationEntry, data)
    }

    public fun visitKtFilePMD(file: KtFile, data: Any?): Any? {
        toKtVisitor().visitFile(file)
        return data
    }

    public fun visitScriptPMD(script: KtScript, data: Any?): Any? {
        return visitDeclarationPMD(script, data)
    }

    public fun visitImportDirectivePMD(importDirective: KtImportDirective, data: Any?): Any? {
        return visitKtElementPMD(importDirective, data)
    }

    public fun visitImportListPMD(importList: KtImportList, data: Any?): Any? {
        return visitKtElementPMD(importList, data)
    }

    public fun visitFileAnnotationListPMD(fileAnnotationList: KtFileAnnotationList, data: Any?): Any? {
        return visitKtElementPMD(fileAnnotationList, data)
    }

    public fun visitClassBodyPMD(classBody: KtClassBody, data: Any?): Any? {
        return visitKtElementPMD(classBody, data)
    }

    public fun visitModifierListPMD(list: KtModifierList, data: Any?): Any? {
        return visitKtElementPMD(list, data)
    }

    public fun visitAnnotationPMD(annotation: KtAnnotation, data: Any?): Any? {
        return visitKtElementPMD(annotation, data)
    }

    public fun visitAnnotationEntryPMD(annotationEntry: KtAnnotationEntry, data: Any?): Any? {
        return visitKtElementPMD(annotationEntry, data)
    }

    public fun visitConstructorCalleeExpressionPMD(constructorCalleeExpression: KtConstructorCalleeExpression, data: Any?): Any? {
        return visitKtElementPMD(constructorCalleeExpression, data)
    }

    public fun visitTypeParameterListPMD(list: KtTypeParameterList, data: Any?): Any? {
        return visitKtElementPMD(list, data)
    }

    public fun visitTypeParameterPMD(parameter: KtTypeParameter, data: Any?): Any? {
        return visitNamedDeclarationPMD(parameter, data)
    }

    public fun visitEnumEntryPMD(enumEntry: KtEnumEntry, data: Any?): Any? {
        return visitClassPMD(enumEntry, data)
    }

    public fun visitParameterListPMD(list: KtParameterList, data: Any?): Any? {
        return visitKtElementPMD(list, data)
    }

    public fun visitParameterPMD(parameter: KtParameter, data: Any?): Any? {
        return visitNamedDeclarationPMD(parameter, data)
    }

    public fun visitConstructorDelegationCallPMD(call: KtConstructorDelegationCall, data: Any?): Any? {
        return visitKtElementPMD(call, data)
    }

    public fun visitPropertyDelegatePMD(delegate: KtPropertyDelegate, data: Any?): Any? {
        return visitKtElementPMD(delegate, data)
    }

    public fun visitTypeReferencePMD(typeReference: KtTypeReference, data: Any?): Any? {
        return visitKtElementPMD(typeReference, data)
    }

    public fun visitValueArgumentListPMD(list: KtValueArgumentList, data: Any?): Any? {
        return visitKtElementPMD(list, data)
    }

    public fun visitArgumentPMD(argument: KtValueArgument, data: Any?): Any? {
        return visitKtElementPMD(argument, data)
    }

    public fun visitExpressionPMD(expression: KtExpression, data: Any?): Any? {
        return visitKtElementPMD(expression, data)
    }

    public fun visitLoopExpressionPMD(loopExpression: KtLoopExpression, data: Any?): Any? {
        return visitExpressionPMD(loopExpression, data)
    }

    public fun visitConstantExpressionPMD(expression: KtConstantExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    public fun visitSimpleNameExpressionPMD(expression: KtSimpleNameExpression, data: Any?): Any? {
        return visitReferenceExpressionPMD(expression, data)
    }

    public fun visitReferenceExpressionPMD(expression: KtReferenceExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    public fun visitLabeledExpressionPMD(expression: KtLabeledExpression, data: Any?): Any? {
        return visitExpressionWithLabelPMD(expression, data)
    }

    public fun visitPrefixExpressionPMD(expression: KtPrefixExpression, data: Any?): Any? {
        return visitUnaryExpressionPMD(expression, data)
    }

    public fun visitPostfixExpressionPMD(expression: KtPostfixExpression, data: Any?): Any? {
        return visitUnaryExpressionPMD(expression, data)
    }

    public fun visitUnaryExpressionPMD(expression: KtUnaryExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    public fun visitBinaryExpressionPMD(expression: KtBinaryExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    public fun visitReturnExpressionPMD(expression: KtReturnExpression, data: Any?): Any? {
        return visitExpressionWithLabelPMD(expression, data)
    }

    public fun visitExpressionWithLabelPMD(expression: KtExpressionWithLabel, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    public fun visitThrowExpressionPMD(expression: KtThrowExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    public fun visitBreakExpressionPMD(expression: KtBreakExpression, data: Any?): Any? {
        return visitExpressionWithLabelPMD(expression, data)
    }

    public fun visitContinueExpressionPMD(expression: KtContinueExpression, data: Any?): Any? {
        return visitExpressionWithLabelPMD(expression, data)
    }

    public fun visitIfExpressionPMD(expression: KtIfExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    public fun visitWhenExpressionPMD(expression: KtWhenExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    public fun visitTryExpressionPMD(expression: KtTryExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    public fun visitForExpressionPMD(expression: KtForExpression, data: Any?): Any? {
        return visitLoopExpressionPMD(expression, data)
    }

    public fun visitWhileExpressionPMD(expression: KtWhileExpression, data: Any?): Any? {
        return visitLoopExpressionPMD(expression, data)
    }

    public fun visitDoWhileExpressionPMD(expression: KtDoWhileExpression, data: Any?): Any? {
        return visitLoopExpressionPMD(expression, data)
    }

    public fun visitAnnotatedExpressionPMD(expression: KtAnnotatedExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    public fun visitCallExpressionPMD(expression: KtCallExpression, data: Any?): Any? {
        return visitReferenceExpressionPMD(expression, data)
    }

    public fun visitArrayAccessExpressionPMD(expression: KtArrayAccessExpression, data: Any?): Any? {
        return visitReferenceExpressionPMD(expression, data)
    }

    public fun visitQualifiedExpressionPMD(expression: KtQualifiedExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    public fun visitDoubleColonExpressionPMD(expression: KtDoubleColonExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    public fun visitCallableReferenceExpressionPMD(expression: KtCallableReferenceExpression, data: Any?): Any? {
        return visitDoubleColonExpressionPMD(expression, data)
    }

    public fun visitClassLiteralExpressionPMD(expression: KtClassLiteralExpression, data: Any?): Any? {
        return visitDoubleColonExpressionPMD(expression, data)
    }

    public fun visitDotQualifiedExpressionPMD(expression: KtDotQualifiedExpression, data: Any?): Any? {
        return visitQualifiedExpressionPMD(expression, data)
    }

    public fun visitSafeQualifiedExpressionPMD(expression: KtSafeQualifiedExpression, data: Any?): Any? {
        return visitQualifiedExpressionPMD(expression, data)
    }

    public fun visitObjectLiteralExpressionPMD(expression: KtObjectLiteralExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    public fun visitBlockExpressionPMD(expression: KtBlockExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    public fun visitCatchSectionPMD(catchClause: KtCatchClause, data: Any?): Any? {
        return visitKtElementPMD(catchClause, data)
    }

    public fun visitFinallySectionPMD(finallySection: KtFinallySection, data: Any?): Any? {
        return visitKtElementPMD(finallySection, data)
    }

    public fun visitTypeArgumentListPMD(typeArgumentList: KtTypeArgumentList, data: Any?): Any? {
        return visitKtElementPMD(typeArgumentList, data)
    }

    public fun visitThisExpressionPMD(expression: KtThisExpression, data: Any?): Any? {
        return visitExpressionWithLabelPMD(expression, data)
    }

    public fun visitSuperExpressionPMD(expression: KtSuperExpression, data: Any?): Any? {
        return visitExpressionWithLabelPMD(expression, data)
    }

    public fun visitParenthesizedExpressionPMD(expression: KtParenthesizedExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    public fun visitInitializerListPMD(list: KtInitializerList, data: Any?): Any? {
        return visitKtElementPMD(list, data)
    }

    public fun visitAnonymousInitializerPMD(initializer: KtAnonymousInitializer, data: Any?): Any? {
        return visitDeclarationPMD(initializer, data)
    }

    public fun visitPropertyAccessorPMD(accessor: KtPropertyAccessor, data: Any?): Any? {
        return visitDeclarationPMD(accessor, data)
    }

    public fun visitTypeConstraintListPMD(list: KtTypeConstraintList, data: Any?): Any? {
        return visitKtElementPMD(list, data)
    }

    public fun visitTypeConstraintPMD(constraint: KtTypeConstraint, data: Any?): Any? {
        return visitKtElementPMD(constraint, data)
    }

    private fun visitTypeElementPMD(type: KtTypeElement, data: Any?): Any? {
        return visitKtElementPMD(type, data)
    }

    public fun visitUserTypePMD(type: KtUserType, data: Any?): Any? {
        return visitTypeElementPMD(type, data)
    }

    public fun visitDynamicTypePMD(type: KtDynamicType, data: Any?): Any? {
        return visitTypeElementPMD(type, data)
    }

    public fun visitFunctionTypePMD(type: KtFunctionType, data: Any?): Any? {
        return visitTypeElementPMD(type, data)
    }

    public fun visitSelfTypePMD(type: KtSelfType, data: Any?): Any? {
        return visitTypeElementPMD(type, data)
    }

    public fun visitBinaryWithTypeRHSExpressionPMD(expression: KtBinaryExpressionWithTypeRHS, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    public fun visitStringTemplateExpressionPMD(expression: KtStringTemplateExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    public fun visitNamedDeclarationPMD(declaration: KtNamedDeclaration, data: Any?): Any? {
        return visitDeclarationPMD(declaration, data)
    }

    public fun visitNullableTypePMD(nullableType: KtNullableType, data: Any?): Any? {
        return visitTypeElementPMD(nullableType, data)
    }

    public fun visitTypeProjectionPMD(typeProjection: KtTypeProjection, data: Any?): Any? {
        return visitKtElementPMD(typeProjection, data)
    }

    public fun visitWhenEntryPMD(KtWhenEntry: KtWhenEntry, data: Any?): Any? {
        return visitKtElementPMD(KtWhenEntry, data)
    }

    public fun visitIsExpressionPMD(expression: KtIsExpression, data: Any?): Any? {
        return visitExpressionPMD(expression, data)
    }

    public fun visitWhenConditionIsPatternPMD(condition: KtWhenConditionIsPattern, data: Any?): Any? {
        return visitKtElementPMD(condition, data)
    }

    public fun visitWhenConditionInRangePMD(condition: KtWhenConditionInRange, data: Any?): Any? {
        return visitKtElementPMD(condition, data)
    }

    public fun visitWhenConditionWithExpressionPMD(condition: KtWhenConditionWithExpression, data: Any?): Any? {
        return visitKtElementPMD(condition, data)
    }

    public fun visitObjectDeclarationPMD(declaration: KtObjectDeclaration, data: Any?): Any? {
        return visitNamedDeclarationPMD(declaration, data)
    }

    public fun visitStringTemplateEntryPMD(entry: KtStringTemplateEntry, data: Any?): Any? {
        return visitKtElementPMD(entry, data)
    }

    public fun visitStringTemplateEntryWithExpressionPMD(entry: KtStringTemplateEntryWithExpression, data: Any?): Any? {
        return visitStringTemplateEntryPMD(entry, data)
    }

    public fun visitBlockStringTemplateEntryPMD(entry: KtBlockStringTemplateEntry, data: Any?): Any? {
        return visitStringTemplateEntryWithExpressionPMD(entry, data)
    }

    public fun visitSimpleNameStringTemplateEntryPMD(entry: KtSimpleNameStringTemplateEntry, data: Any?): Any? {
        return visitStringTemplateEntryWithExpressionPMD(entry, data)
    }

    public fun visitLiteralStringTemplateEntryPMD(entry: KtLiteralStringTemplateEntry, data: Any?): Any? {
        return visitStringTemplateEntryPMD(entry, data)
    }

    public fun visitEscapeStringTemplateEntryPMD(entry: KtEscapeStringTemplateEntry, data: Any?): Any? {
        return visitStringTemplateEntryPMD(entry, data)
    }

    public fun visitPackageDirectivePMD(directive: KtPackageDirective, data: Any?): Any? {
        return visitElementPMD(directive)
    }
}