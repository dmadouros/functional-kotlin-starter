package me.dmadouros

import assertk.assertThat
import assertk.assertions.isEqualTo
import me.dmadouros.Tree.Branch
import me.dmadouros.Tree.Leaf
import org.junit.jupiter.api.Test

class TreeTest {
    @Test
    fun testSize() {
        val one = Leaf(1)
        val two = Leaf(2)
        val three = Leaf(3)

        assertThat(Tree.size(one)).isEqualTo(1)
        assertThat(Tree.size(Branch(one, Branch(two, three)))).isEqualTo(3)
    }

    @Test
    fun testMax() {
        val one = Leaf(1)
        val two = Leaf(2)
        val three = Leaf(3)
        val four = Leaf(4)
        val five = Leaf(5)
        val six = Leaf(6)

        val tree = Branch(one, Branch(two, Branch(three, Branch(four, Branch(five, six)))))

        assertThat(Tree.max(tree)).isEqualTo(6)
    }

    @Test
    fun testDepth() {
        val one = Leaf(1)
        val two = Leaf(2)
        val three = Leaf(3)
        val four = Leaf(4)
        val five = Leaf(5)
        val six = Leaf(6)

        val tree =
            Branch(
                one,
                Branch(
                    two,
                    Branch(
                        three,
                        Branch(
                            four,
                            Branch(
                                five,
                                six,
                            ),
                        ),
                    ),
                ),
            )

        assertThat(Tree.depth(one)).isEqualTo(1)
        assertThat(Tree.depth(Branch(one, two))).isEqualTo(2)
        assertThat(Tree.depth(tree)).isEqualTo(6)
    }

    @Test
    fun testMap() {
        val one = Leaf(1)
        val two = Leaf(2)
        val three = Leaf(3)
        val four = Leaf(4)
        val five = Leaf(5)
        val six = Leaf(6)

        val tree = Branch(one, Branch(two, Branch(three, Branch(four, Branch(five, six)))))

        assertThat(Tree.map(one) { it + 1 }).isEqualTo(Leaf(2))
        assertThat(Tree.map(Branch(one, two)) { it + 1 }).isEqualTo(Branch(Leaf(2), Leaf(3)))
        assertThat(Tree.map(tree) { it + 1 }).isEqualTo(
            Branch(
                Leaf(2),
                Branch(
                    Leaf(3),
                    Branch(
                        Leaf(4),
                        Branch(
                            Leaf(5),
                            Branch(
                                Leaf(6),
                                Leaf(7),
                            ),
                        ),
                    ),
                ),
            ),
        )
    }

    @Test
    fun testFold() {
        assertThat(Tree.fold(Leaf(1), { x -> x }, { b1, b2 -> b1 + b2 })).isEqualTo(1)
        assertThat(Tree.fold(Branch(Leaf(1), Leaf(2)), { x -> x }, { b1, b2 -> b1 + b2 })).isEqualTo(3)

        /*
         * Before moving on:
         * 1. Rewrite size using fold
         * 2. Rewrite max using fold
         * 3. Rewrite depth using fold
         * 4. Rewrite map using fold
         */
    }
}
