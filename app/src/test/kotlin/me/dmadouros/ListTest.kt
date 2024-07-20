package me.dmadouros

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.hasMessage
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class ListTest {
    @Test
    fun testTail() {
        assertFailure { List.Nil.tail() }.hasMessage("cannot call tail on empty list")
        assertThat((List.of(1, 2, 3).tail())).isEqualTo(List.of(2, 3))
    }

    @Test
    fun testSetHead() {
        assertThat(List.Nil.setHead(1)).isEqualTo(List.of(1))
        assertThat(List.of(2, 3).setHead(1)).isEqualTo(List.of(1, 2, 3))
    }

    @Test
    @Disabled
    fun testDrop() {
        assertThat(List.Nil.drop(1)).isEqualTo(List.empty())
        assertThat(List.of(1, 2, 3).drop(1)).isEqualTo(List.of(2, 3))
        assertThat(List.of(1, 2, 3).drop(2)).isEqualTo(List.of(3))
        assertThat(List.of(1, 2, 3).drop(3)).isEqualTo(List.empty())
    }

    @Test
    @Disabled
    fun testDropWhile() {
        assertThat(List.Nil.dropWhile { true }).isEqualTo(List.empty())
        assertThat(List.of(1, 2, 3).dropWhile { it < 2 }).isEqualTo(List.of(2, 3))
        assertThat(List.of(1, 2, 3).dropWhile { it < 3 }).isEqualTo(List.of(3))
        assertThat(List.of(1, 2, 3).dropWhile { it < 4 }).isEqualTo(List.empty())
    }

    @Test
    @Disabled
    fun testAppend() {
        assertThat(List.of(1, 2, 3).append(List.of(4, 5, 6))).isEqualTo(List.of(1, 2, 3, 4, 5, 6))
        assertThat(List.Nil.append(List.of(4, 5, 6))).isEqualTo(List.of(4, 5, 6))
        assertThat(List.of(1, 2, 3).append(List.Nil)).isEqualTo(List.of(1, 2, 3))
        assertThat(List.Nil.append(List.Nil)).isEqualTo(List.Nil)
    }

    @Test
    @Disabled
    fun testInit() {
        assertFailure { List.Nil.init() }.hasMessage("cannot init Nil list")
        assertThat(List.of(1, 2, 3, 4).init()).isEqualTo(List.of(1, 2, 3))
    }

    @Test
    @Disabled
    fun testSum() {
        assertThat(List.Nil.sum()).isEqualTo(0)
        assertThat(List.of(1, 2, 3, 4).sum()).isEqualTo(10)
    }

    @Test
    @Disabled
    fun testProduct() {
        assertThat(List.Nil.product()).isEqualTo(1.0)
        assertThat(List.of(1.0, 2.0, 3.0, 4.0).product()).isEqualTo(24.0)
    }

    @Test
    @Disabled
    fun testFoldRight() {
        assertThat(
            List.Nil.foldRight(List.empty<Int>()) { x, y -> List.Cons(x, y) },
        ).isEqualTo(List.empty())
        assertThat(
            List.of(1, 2, 3).foldRight(List.empty<Int>()) { x, y -> List.Cons(x, y) },
        ).isEqualTo(List.of(1, 2, 3))

        /*
         * Before moving on:
         * 1. Rewrite sum using foldRight
         * 2. Rewrite product using foldRight
         */
    }

    @Test
    @Disabled
    fun testLength() {
        assertThat(List.Nil.length()).isEqualTo(0)
        assertThat(List.of(1, 2, 3, 4).length()).isEqualTo(4)
    }

    @Test
    @Disabled
    fun testFoldLeft() {
        assertThat(List.empty<Int>().foldLeft(0) { y, x -> x + y }).isEqualTo(0)
        assertThat(List.of(1, 2, 3).foldLeft(0) { y, x -> x + y }).isEqualTo(6)
        assertThat(List.of(2, 4, 6).foldLeft(1) { y, x -> x * y }).isEqualTo(48)

        /*
         * Before moving on:
         * 1. Rewrite sum using foldLeft
         * 2. Rewrite product using foldLeft
         * 3. Rewrite length using foldLeft
         */
    }

    @Test
    @Disabled
    fun testReverse() {
        assertThat(List.Nil.reverse()).isEqualTo(List.Nil)
        assertThat(List.of(1, 2, 3).reverse()).isEqualTo(List.of(3, 2, 1))

        /*
         * Before moving on:
         * 1. Rewrite append using foldLeft and reverse
         * 2. Rewrite append using foldRight
         */
    }

    @Test
    @Disabled
    fun testFlatten() {
        assertThat(List.of(List.Nil).flatten()).isEqualTo(List.Nil)
        assertThat(List.of(List.of(1), List.of(2), List.of(3)).flatten()).isEqualTo(List.of(1, 2, 3))
    }

    @Test
    @Disabled
    fun testAdd1() {
        assertThat(List.Nil.add1()).isEqualTo(List.empty())
        assertThat(List.of(1, 2, 3).add1()).isEqualTo(List.of(2, 3, 4))
    }

    @Test
    @Disabled
    fun testDoubleToString() {
        assertThat(List.Nil.doubleToString()).isEqualTo(List.empty())
        assertThat(List.of(1.0, 2.0, 3.0).doubleToString()).isEqualTo(List.of("1.0", "2.0", "3.0"))
    }

    @Test
    @Disabled
    fun testMap() {
        assertThat(List.empty<Int>().map { x -> x + 1 }).isEqualTo(List.empty())
        assertThat(List.of(1, 2, 3).map { x -> x + 1 }).isEqualTo(List.of(2, 3, 4))
        assertThat(List.of(1.0, 2.0, 3.0).map { x -> x.toString() }).isEqualTo(List.of("1.0", "2.0", "3.0"))
    }

    @Test
    @Disabled
    fun testFilter() {
        assertThat(List.empty<Int>().filter { x -> x % 2 == 0 }).isEqualTo(List.empty())
        assertThat(List.of(1, 2, 3, 4).filter { x -> x % 2 == 0 }).isEqualTo(List.of(2, 4))
    }

    @Test
    @Disabled
    fun testFlatMap() {
        assertThat(List.empty<Int>().flatMap { i -> List.of(i, i) }).isEqualTo(List.empty())
        assertThat(List.of(1, 2, 3).flatMap { i -> List.of(i, i) }).isEqualTo(List.of(1, 1, 2, 2, 3, 3))

        /*
         * Before moving on:
         * 1. Rewrite filter using flatMap
         */
    }

    @Test
    @Disabled
    fun testAddPairs() {
        assertThat(List.Nil.addPairs(List.of(4, 5, 6))).isEqualTo(List.empty())
        assertThat(List.of(1, 2, 3).addPairs(List.empty())).isEqualTo(List.empty())
        assertThat(List.of(1, 2, 3).addPairs(List.of(4, 5, 6))).isEqualTo(List.of(5, 7, 9))
        assertThat(List.of(1, 2, 3).addPairs(List.of(4, 5))).isEqualTo(List.of(5, 7))
        assertThat(List.of(1, 2).addPairs(List.of(4, 5, 6))).isEqualTo(List.of(5, 7))
    }

    @Test
    @Disabled
    fun testZipWith() {
        assertThat(List.Nil.zipWith(List.of(4, 5, 6)) { x, y -> x + y }).isEqualTo(List.empty())
        assertThat(List.of(1, 2, 3).zipWith(List.empty()) { x, y -> x + y }).isEqualTo(List.empty())
        assertThat(List.of(1, 2, 3).zipWith(List.of(4, 5, 6)) { x, y -> x + y }).isEqualTo(List.of(5, 7, 9))
        assertThat(List.of(1, 2, 3).zipWith(List.of(4, 5)) { x, y -> x + y }).isEqualTo(List.of(5, 7))
        assertThat(List.of(1, 2).zipWith(List.of(4, 5, 6)) { x, y -> x + y }).isEqualTo(List.of(5, 7))
    }

    @Test
    @Disabled
    fun testTake() {
        assertThat(List.Nil.take(2)).isEqualTo(List.empty())
        assertThat(List.of(1, 2, 3).take(0)).isEqualTo(List.empty())
        assertThat(List.of(1, 2, 3).take(1)).isEqualTo(List.of(1))
        assertThat(List.of(1, 2, 3).take(2)).isEqualTo(List.of(1, 2))
        assertThat(List.of(1, 2, 3).take(3)).isEqualTo(List.of(1, 2, 3))
        assertThat(List.of(1, 2, 3).take(4)).isEqualTo(List.of(1, 2, 3))
    }

    @Test
    @Disabled
    fun testHasSubsequence() {
        val list1 = List.of(1, 2, 3, 4)
        val list2 = List.of(1, 2)
        val list3 = List.of(2, 3)
        val list4 = List.of(4)
        val list5 = List.of(5)

        assertThat(List.Nil.hasSubsequence(list2)).isFalse()
        assertThat(list1.hasSubsequence(List.Nil)).isTrue()
        assertThat(list1.hasSubsequence(list2)).isTrue()
        assertThat(list1.hasSubsequence(list3)).isTrue()
        assertThat(list1.hasSubsequence(list4)).isTrue()
        assertThat(list1.hasSubsequence(list5)).isFalse()
    }
}
