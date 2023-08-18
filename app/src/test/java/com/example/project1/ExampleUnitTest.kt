package com.example.project1

import com.example.project1.util.timestampToTime
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}

class UnitTest {
    @Test
    fun timeTesting() {
        println(timestampToTime(1630347600))
    }
}