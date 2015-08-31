package org.jetbrains.pmdkotlin.lang.kotlin

import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Disposer
import com.intellij.psi.PsiManager
import org.jetbrains.kotlin.cli.jvm.compiler.EnvironmentConfigFiles
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.parsing.JetParser
import org.jetbrains.kotlin.parsing.JetParserDefinition
//
//class KotlinFileContext {
//    init {
//        val configuration = CompilerConfiguration()
//        val rootDisposable = Disposer.newDisposable()
//        val environment = KotlinCoreEnvironment.createForProduction(rootDisposable, configuration, EnvironmentConfigFiles.JVM_CONFIG_FILES)
//
//        project: Project = environment.getProject()
//        manager = PsiManager.getInstance(project)
//        parserDefinition = JetParserDefinition.getInstance()
//        parser = parserDefinition.createParser(project)
//    }
//}