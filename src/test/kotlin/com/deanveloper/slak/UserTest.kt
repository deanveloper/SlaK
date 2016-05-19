package com.deanveloper.slak

import org.junit.Assert.*
import org.junit.Test

/**
 * Tests the User class
 *
 * @author Dean B
 */
class UserTest : BaseTest() {
    @Test fun testUser() {
        for(u in User.list) {
            println(u)
        }
    }
}
