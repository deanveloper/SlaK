package com.deanveloper.slak.channel

import com.deanveloper.slak.SetupTest
import com.deanveloper.slak.User
import org.testng.annotations.Test

/**
 * @author Dean B
 */
class GroupTest {
    @Test
    fun test() {
        assert(Group["G0PBRDW79"] == Group["#dev"])
        assert(User["@deanbot"] in Group["#dev"].members)
    }
}