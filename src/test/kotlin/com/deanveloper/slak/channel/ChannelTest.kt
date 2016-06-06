package com.deanveloper.slak.channel

import com.deanveloper.slak.User
import com.deanveloper.slak.util.Cacher
import org.testng.annotations.Test

/**
 * @author Dean B
 */
class ChannelTest {

    @Test
    fun channelName() {
        assert(Channel["C0PE0RP9C"] == Channel["#off-topic"])
    }

    @Test
    fun channelOwner() {
        assert(Channel["C0PE0RP9C"].creator == User["@pixll"])
    }

    @Test
    fun nullChannel() {
        assert(null == Channel.getOrNull("#not-a-channel"))
    }

    @Test(expectedExceptions = arrayOf(Cacher.IndexNotFoundException::class))
    fun invalidChannel() {
        Channel["#not-a-channel"]
    }

    @Test(expectedExceptions = arrayOf(HistoryNotLoadedException::class))
    fun invalidHistory() {
        Channel["#off-topic"].history
    }


    @Test(timeOut = 5000, dependsOnMethods = arrayOf("invalidHistory"))
    fun channelHistory() {
        var wait: Boolean = true
        Channel["#off-topic"].historyAt {

            wait = false
        }

        while (wait);
    }
}