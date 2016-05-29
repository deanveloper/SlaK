package com.deanveloper.slak.im

import com.deanveloper.slak.BaseTest
import com.deanveloper.slak.User
import com.deanveloper.slak.channel.Channel
import org.junit.Assert.*
import org.junit.Test

/**
 * @author Dean B
 */
class ImChatTest : BaseTest() {
    @Test
    fun testIm() {
        assertEquals(ImChat[User["@dean"].id], ImChat["D18MP8E0M"])
    }
}