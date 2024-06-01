package me.dmadouros

sealed class List<out A> {
    companion object {
        fun <A> of(vararg aa: A): List<A> {
            val tail = aa.sliceArray(1 until aa.size)
            return if (aa.isEmpty()) Nil else Cons(aa[0], of(*tail))
        }

        fun <A> tail(xs: List<A>): List<A> =
            when (xs) {
                is Nil -> throw Exception("cannot call tail on empty list")
                is Cons -> xs.tail
            }

        fun <A> setHead(
            xs: List<A>,
            x: A,
        ): List<A> = Cons(x, xs)
    }
}

data object Nil : List<Nothing>()

data class Cons<out A>(val head: A, val tail: List<A>) : List<A>()
