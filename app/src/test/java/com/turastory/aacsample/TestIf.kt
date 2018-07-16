package com.turastory.aacsample

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TestIf {
    @Test
    fun returnIsNull() {
        val testClass = TestClass()
        assertNull(testClass.returnsNullIfZero(0))
        receiveNotNullString(testClass.returnsNullIfZero(0))
    }

    fun receiveNotNullString(item: String) {
        print(item)
    }
}
