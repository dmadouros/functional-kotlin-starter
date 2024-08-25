package me.dmadouros

sealed class Tree<out A> {
    companion object {
        fun <A> size(t: Tree<A>): Int = TODO()

        fun max(t: Tree<Int>): Int = TODO()

        fun <A> depth(t: Tree<A>): Int = TODO()

        fun <A, B> map(
            t: Tree<A>,
            f: (A) -> B,
        ): Tree<B> = TODO()

        fun <A, B> fold(
            t: Tree<A>,
            f: (A) -> B,
            g: (B, B) -> B,
        ): B = TODO()
    }

    data class Leaf<A>(
        val value: A,
    ) : Tree<A>()

    data class Branch<A>(
        val left: Tree<A>,
        val right: Tree<A>,
    ) : Tree<A>()
}
