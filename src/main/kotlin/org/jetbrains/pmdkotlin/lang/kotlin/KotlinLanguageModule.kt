package org.jetbrains.pmdkotlin.lang.kotlin

import net.sourceforge.pmd.lang.BaseLanguageModule
import org.jetbrains.kotlin.builtins.DefaultBuiltIns
import org.jetbrains.kotlin.builtins.KotlinBuiltIns
import org.jetbrains.kotlin.descriptors.ClassifierDescriptor
import org.jetbrains.kotlin.descriptors.SupertypeLoopChecker
import org.jetbrains.kotlin.descriptors.TypeParameterDescriptor
import org.jetbrains.kotlin.descriptors.annotations.Annotations
import org.jetbrains.kotlin.storage.LockBasedStorageManager
import org.jetbrains.kotlin.types.AbstractTypeConstructor
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.pmdkotlin.lang.kotlin.rule.KotlinRuleChainVisitor

class KotlinLanguageModule : BaseLanguageModule(KotlinLanguageModule.NAME, null, KotlinLanguageModule.TERSE_NAME, KotlinRuleChainVisitor::class.java, KotlinLanguageModule.FILE_EXTENSION) {

    init {
        addVersion("1.0.3", KotlinHandler(), true)

        initWorkaround()
    }

    /**
     * Workaround for: https://youtrack.jetbrains.com/issue/KT-13264
     */
    private fun initWorkaround() {
        object : AbstractTypeConstructor(LockBasedStorageManager.NO_LOCKS) {
            override fun getAnnotations(): Annotations = Annotations.EMPTY
            override fun getParameters(): List<TypeParameterDescriptor> = emptyList()
            override fun isFinal(): Boolean = true
            override fun isDenotable(): Boolean = true
            override fun getDeclarationDescriptor(): ClassifierDescriptor = DefaultBuiltIns.Instance.boolean
            override fun getBuiltIns(): KotlinBuiltIns = DefaultBuiltIns.Instance
            override fun computeSupertypes(): Collection<KotlinType> = emptyList()

            override val supertypeLoopChecker: SupertypeLoopChecker
                get() = SupertypeLoopChecker.EMPTY
        }
    }

    companion object {
        val NAME = "Kotlin"
        val TERSE_NAME = "kotlin"

        private val FILE_EXTENSION = "kt"
    }

}
