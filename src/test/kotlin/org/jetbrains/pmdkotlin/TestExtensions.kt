package org.jetbrains.pmdkotlin

import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.net.URISyntaxException

fun Any.getResource(path: String): File {
    return File(javaClass.getResource(path).toURI())
}

fun Any.getResourcePath(path: String): String = getResource(path).getAbsolutePath()

fun String.asKtFile(): File {
    val file = File.createTempFile("prefix", ".kt")
    FileWriter(file).use { it.write(this) }
    return file
}

fun String.asKtPath(): String = asKtFile().getAbsolutePath()

