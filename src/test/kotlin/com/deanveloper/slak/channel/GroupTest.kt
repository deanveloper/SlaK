package com.deanveloper.slak.channel

import com.deanveloper.slak.BaseTest
import com.deanveloper.slak.User
import org.junit.Assert.*
import org.junit.Test

/**
 * @author Dean B
 */
class GroupTest : BaseTest() {
    @Test fun test() {
        assertEquals(Group["G0PBRDW79"], Group["#dev"])
        assertTrue(User["@deanbot"] in Group["#dev"].members)
    }
}