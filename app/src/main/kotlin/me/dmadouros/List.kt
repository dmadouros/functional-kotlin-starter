package me.dmadouros

import me.dmadouros.List.Cons
import me.dmadouros.List.Nil

sealed class List<out A> {
    companion object {
        fun <A> of(vararg aa: A): List<A> {
            val tail = aa.sliceArray(1 until aa.size)
            return if (aa.isEmpty()) Nil else Cons(aa[0], of(*tail))
        }

        fun <A> empty(): List<A> = Nil

        fun <A> cons(
            x: A,
            xs: List<A>,
        ): List<A> = Cons(x, xs)
    }

    data object Nil : List<Nothing>()

    data class Cons<out A>(
        val head: A,
        val tail: List<A>,
    ) : List<A>()
}

fun <A> List<A>.tail(): List<A> =
    when (this) {
        is Nil -> throw Exception("cannot call tail on empty list")
        is Cons -> this.tail
    }

fun <A> List<A>.setHead(x: A): List<A> = List.cons(x, this)

tailrec fun <A> List<A>.drop(n: Int): List<A> =
    when (this) {
        is Nil -> this
        is Cons -> if (n > 0) this.tail.drop(n - 1) else this
    }

tailrec fun <A> List<A>.dropWhile(f: (A) -> Boolean): List<A> =
    when (this) {
        is Nil -> this
        is Cons -> if (f(this.head)) this.tail.dropWhile(f) else this
    }

fun <A> List<A>.append(xs: List<A>): List<A> {
    return when (this) {
        is Nil -> xs
        is Cons -> {
            when (xs) {
                is Nil -> this
                is Cons -> Cons(this.head, this.tail.append(xs))
            }
        }
    }
}

fun <A> List<A>.init(): List<A> {
    fun go(
        x: A,
        xs: List<A>,
    ): List<A> =
        when (xs) {
            is Nil -> Nil
            is Cons -> Cons(x, go(xs.head, xs.tail))
        }

    return when (this) {
        is Nil -> throw Exception("cannot init Nil list")
        is Cons -> go(this.head, this.tail)
    }
}

fun List<Int>.sum(): Int = this.foldLeft(0) { x: Int, y: Int -> x + y }

fun List<Double>.product(): Double = this.foldLeft(1.0) { x: Double, y: Double -> x * y }

fun <A, B> List<A>.foldRight(
    z: B,
    f: (A, B) -> B,
): B =
    when (this) {
        is Nil -> z
        is Cons -> f(this.head, this.tail.foldRight(z, f))
    }

fun <A> List<A>.length(): Int = this.foldLeft(0) { y: Int, _ -> y + 1 }

tailrec fun <A, B> List<A>.foldLeft(
    z: B,
    f: (B, A) -> B,
): B =
    when (this) {
        is Nil -> z
        is Cons -> this.tail.foldLeft(f(z, this.head), f)
    }

fun <A> List<A>.reverse(): List<A> = foldLeft(List.empty()) { xs, x -> Cons(x, xs) }

fun <A> List<List<A>>.flatten(): List<A> = this.foldLeft(List.empty()) { ys, y -> ys.append(y) }

fun List<Int>.add1(): List<Int> = this.foldRight(List.empty()) { x, xs -> Cons(x + 1, xs) }

fun List<Double>.doubleToString(): List<String> = this.foldRight(List.empty()) { x, xs -> Cons(x.toString(), xs) }

fun <A, B> List<A>.map(f: (A) -> B): List<B> = this.foldRight(List.empty()) { x, xs -> Cons(f(x), xs) }

fun <A> List<A>.filter(p: (A) -> Boolean): List<A> = this.foldRight(List.empty()) { x, xs -> if (p(x)) Cons(x, xs) else xs }

fun <A, B> List<A>.flatMap(f: (A) -> List<B>): List<B> = this.map(f).flatten()

fun List<Int>.addPairs(ys: List<Int>): List<Int> {
    fun go(
        xs: List<Int>,
        ys: List<Int>,
        zs: List<Int>,
    ): List<Int> =
        when (xs) {
            is Nil -> zs
            is Cons -> {
                when (ys) {
                    is Nil -> zs
                    is Cons -> Cons(xs.head + ys.head, go(xs.tail, ys.tail, zs))
                }
            }
        }

    return go(this, ys, List.empty())
}

fun <A> List<A>.zipWith(
    ys: List<A>,
    f: (A, A) -> A,
): List<A> {
    fun go(
        xs: List<A>,
        ys: List<A>,
        zs: List<A>,
    ): List<A> =
        when (xs) {
            is Nil -> zs
            is Cons -> {
                when (ys) {
                    is Nil -> zs
                    is Cons -> Cons(f(xs.head, ys.head), go(xs.tail, ys.tail, zs))
                }
            }
        }

    return go(this, ys, List.empty())
}

fun <A> List<A>.take(n: Int): List<A> {
    tailrec fun go(
        xs: List<A>,
        n: Int,
        ys: List<A>,
    ): List<A> =
        if (n == 0) {
            ys
        } else {
            when (xs) {
                is Nil -> ys
                is Cons -> go(xs.tail, n - 1, Cons(xs.head, ys))
            }
        }

    return go(this, n, List.empty()).reverse()
}

fun <A> List<A>.hasSubsequence(ys: List<A>): Boolean =
    if (this.take(ys.length()) == ys) {
        true
    } else {
        when (this) {
            is Nil -> false
            is Cons -> this.tail.hasSubsequence(ys)
        }
    }
