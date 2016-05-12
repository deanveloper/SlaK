package com.deanveloper.slak

import org.junit.BeforeClass

/**
 * The main class for JUnit tests
 *
 * @author Dean B
 */
open class BaseTest {
    companion object {
        @BeforeClass @JvmStatic fun setUpBaseClass() {
            start()
        }
    }
}
