package com.deanveloper.slak

import org.testng.annotations.BeforeSuite
import java.net.URI
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

/**
 * The main class for JUnit tests
 *
 * @author Dean B
 */
class SetupTest {
    var initialized = false
    val CIPHER = Cipher.getInstance("AES")
    val KEY = SecretKeySpec(Files.readAllBytes(Paths.get(System.getProperty("user.home"), "pass.txt")), "AES")
    val encryptedToken = byteArrayOf(0xd5.toByte(), 0x7b, 0x79, 0x48, 0x0b, 0xaf.toByte(), 0x72, 0x8c.toByte(),
            0x07, 0x27, 0x76, 0x8e.toByte(), 0x15, 0x0e, 0x49, 0x35, 0x6e, 0x3f, 0x02, 0xf9.toByte(), 0x3e, 0x55,
            0xb5.toByte(), 0xde.toByte(), 0xee.toByte(), 0xcf.toByte(), 0x4c, 0x71, 0x47, 0xe1.toByte(), 0x5e, 0x2a,
            0xe5.toByte(), 0x25, 0x0c, 0x83.toByte(), 0x0d, 0x52, 0xd0.toByte(), 0x9d.toByte(), 0x22, 0x28,
            0x81.toByte(), 0x1a, 0xd9.toByte(), 0x18, 0xe6.toByte(), 0xd9.toByte())


    @BeforeSuite
    fun setUpBaseClass() {
        if (!initialized) {
            initialized = true
        } else {
            return
        }

        CIPHER.init(Cipher.DECRYPT_MODE, KEY)
        TOKEN = CIPHER.doFinal(encryptedToken).toString(Charset.defaultCharset())

        BASE_URL = URI("https://nodestone.slack.com")

        var done = false
        start {
            done = true
        }
        while (!done);
    }
}
