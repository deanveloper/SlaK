package com.deanveloper.slak

import org.testng.annotations.Test

/**
 * Tests the User class
 *
 * @author Dean B
 */
class UserTest {
    @Test
    fun testUser() {
        assert(User["U0P9UTP62"] == User["@adamjle"])
        assert(User["@dean"].profile.email == "deanvelopermn@gmail.com")
    }
}
