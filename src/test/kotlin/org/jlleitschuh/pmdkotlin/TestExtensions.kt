package org.jlleitschuh.pmdkotlin

import java.io.File
import java.io.FileWriter

fun String.asKtFile(): File {
    val file = File.createTempFile("prefix", ".kt")
    FileWriter(file).use { it.write(this) }
    return file
}

fun Any.getResource(path: String): File {
    return File(javaClass.getResource(path).toURI())
}
