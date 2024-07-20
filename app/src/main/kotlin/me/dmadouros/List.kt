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

tailrec fun <A> List<A>.drop(n: Int): List<A> = TODO()

tailrec fun <A> List<A>.dropWhile(f: (A) -> Boolean): List<A> = TODO()

fun <A> List<A>.append(xs: List<A>): List<A> = TODO()

fun <A> List<A>.init(): List<A> = TODO()

tailrec fun List<Int>.sum(): Int = TODO()

tailrec fun List<Double>.product(): Double = TODO()

fun <A, B> List<A>.foldRight(
    z: B,
    f: (A, B) -> B,
): B = TODO()

fun <A> List<A>.length(): Int = TODO()

tailrec fun <A, B> List<A>.foldLeft(
    z: B,
    f: (B, A) -> B,
): B = TODO()

fun <A> List<A>.reverse(): List<A> = TODO()

fun <A> List<List<A>>.flatten(): List<A> = TODO()

fun List<Int>.add1(): List<Int> = TODO()

fun List<Double>.doubleToString(): List<String> = TODO()

fun <A, B> List<A>.map(f: (A) -> B): List<B> = TODO()

fun <A> List<A>.filter(p: (A) -> Boolean): List<A> = TODO()

fun <A, B> List<A>.flatMap(f: (A) -> List<B>): List<B> = TODO()

fun List<Int>.addPairs(ys: List<Int>): List<Int> = TODO()

fun <A> List<A>.zipWith(
    ys: List<A>,
    f: (A, A) -> A,
): List<A> = TODO()

tailrec fun <A> List<A>.take(count: Int): List<A> = TODO()

tailrec fun <A> List<A>.hasSubsequence(ys: List<A>): Boolean = TODO()
