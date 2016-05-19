package com.deanveloper.slak

import org.junit.BeforeClass
import java.net.URI
import java.nio.file.Files
import java.nio.file.Paths
import javax.crypto.Cipher

/**
 * The main class for JUnit tests
 *
 * @author Dean B
 */
open class BaseTest {
    companion object {
        val CIPHER = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC")
        val key = Files.readAllBytes(Paths.get(System.getProperty("user.home"), "Documents", "APIKeyPassword.txt"))

        @BeforeClass @JvmStatic fun setUpBaseClass() {
            TOKEN =
            BASE_URL = URI("https://nodestone.slack.com")
            start() {
                println("list: ${User.list}")
            }
            println("Waiting to establish connection")
            Thread.sleep(5000)
        }
    }
}
