package com.deanveloper.slak.im

import com.deanveloper.slak.User
import org.testng.annotations.Test

/**
 * @author Dean B
 */
class ImChatTest {
    @Test
    fun testIm() {
        assert(ImChat[User["@dean"].id] == ImChat["D18MP8E0M"])
    }
}