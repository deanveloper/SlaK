package com.deanveloper.slak

import org.junit.BeforeClass
import java.net.URI

/**
 * The main class for JUnit tests
 *
 * @author Dean B
 */
open class BaseTest {
    companion object {
        @BeforeClass @JvmStatic fun setUpBaseClass() {
            TOKEN = "xoxb-42726376804-zeceYGGojyCCf84X9Dmwkfiu"
            BASE_URL = URI("https://nodestone.slack.com")
            start() {
                println("list: ${User.list}")
            }
            println("Waiting to establish connection")
            Thread.sleep(5000)
        }
    }
}
