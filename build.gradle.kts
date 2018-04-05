plugins {
    `idea`
    `java`
    kotlin("jvm") version "1.2.31"
}

group = "com.jetbrains.pmd-kotlin"
version = "0.1"

repositories {
    jcenter()
    mavenCentral()
}

val kotlinVersion = property("kotlin.version")
val pmdVersion = property("pmd.version")
dependencies {
    compile("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    compile("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    compile("org.jetbrains.kotlin:kotlin-compiler:$kotlinVersion")
    compile("net.sourceforge.pmd:pmd-core:$pmdVersion")
    compile("net.sourceforge.pmd:pmd-test:$pmdVersion")
    compile("com.intellij:openapi:7.0.3")

    testCompile("junit:junit:4.12")
    testCompile("org.assertj:assertj-core:3.4.1")
}

task<JavaExec>("guiCPD") {
    main = "net.sourceforge.pmd.cpd.GUI"
    classpath = java.sourceSets["main"].runtimeClasspath
    dependsOn("classes")
}
