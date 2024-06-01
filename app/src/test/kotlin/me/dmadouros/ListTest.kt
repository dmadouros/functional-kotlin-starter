package me.dmadouros

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.hasMessage
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class ListTest {
    @Test
    fun testTail() {
        assertThat(List.tail(List.of(1, 2, 3))).isEqualTo(List.of(2, 3))
        assertFailure { List.tail(Nil) }.hasMessage("cannot call tail on empty list")
    }

    @Test
    fun testSetHead() {
        assertThat(List.setHead(Nil, 1)).isEqualTo(List.of(1))
        assertThat(List.setHead(List.of(2, 3), 1)).isEqualTo(List.of(1, 2, 3))
    }
}
