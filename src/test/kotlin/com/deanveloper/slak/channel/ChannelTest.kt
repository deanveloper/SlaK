package com.deanveloper.slak.channel

import com.deanveloper.slak.BaseTest
import com.deanveloper.slak.User
import org.junit.Assert.*
import org.junit.Test

/**
 * @author Dean B
 */
class ChannelTest : BaseTest() {
    @Test fun testChannel() {
        println(Channel.list)
        assertEquals(Channel["C0PE0RP9C"], Channel["#off-topic"])
        assertEquals(Channel["C0PE0RP9C"].creator, User["@pixll"])
    }
}