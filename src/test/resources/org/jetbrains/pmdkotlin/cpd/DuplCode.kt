package org.jetbrains.pmdkotlin.cpd

/**
 * Project: pmd-kotlin
 * Created by Markus Pöschl on 27.07.16.
 */
class DuplCode1 {


    fun bar(text: String): String {
        return text
    }

    fun foobar() {
        val dummyList = mutableListOf<Int>()

        val value1 = 1 + 42
        dummyList.add(value1)

        val value2 = 2 + 42
        dummyList.add(value2)

        val value3 = 3 + 42
        dummyList.add(value3)

        val value4 = 4 + 42
        dummyList.add(value4)
    }
}

class DuplCode2 {


    fun foo(text: String): String {
        return text
    }

    fun foobar() {
        val dummyList = mutableListOf<Int>()

        val value1 = 1 + 42
        dummyList.add(value1)

        val value2 = 2 + 42
        dummyList.add(value2)

        val value3 = 3 + 42
        dummyList.add(value3)

        val value4 = 4 + 42
        dummyList.add(value4)
    }
}