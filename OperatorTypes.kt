package com.example.rxjava

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.TestScheduler
import java.util.concurrent.TimeUnit


public fun main(args: Array<String>) {

    println("1.Filter Operator")
    println("2.Distinct Operator")
    println("3.Take & Takewhile")
    println("4.Buffer Operator")
    println("5.Debounce Operator")
    println("6.Map Operator")
    println("7.FlatMap Operator")
    println("8.Concat Operator")
    println("Enter choice:")
    val (a) = readLine()!!.split('\n')
    val x: Int? = a.toInt()
    when (x) {
        1 -> {
            OperatorTypes().filterOperator()
        }
        2 -> {
            OperatorTypes().distinctOperator();
        }
        3 -> {
            OperatorTypes().take_takewhileOperator();
        }
        4 -> {
            OperatorTypes().bufferOperator();
        }
        5 -> {
            OperatorTypes().debounceOperator();
        }
        6 -> {
            OperatorTypes().map();
        }
        7 -> {
            OperatorTypes().flatMap();
        }
        8 -> {
            OperatorTypes().concatMap();
        }
        else -> { // Note the block
            print("x is neither 1 nor 2")
        }
    }


}

class OperatorTypes {
    fun filterOperator() {
        val numberObservable = Observable.just(1, 2, 3, 4, 6, 7, 5)
        numberObservable.filter { item: Int -> item % 2 == 0 }
                .subscribe(observerCreation())
    }

    fun distinctOperator() {
        val numberObservable = Observable.just(1, 2, 3, 4, 5, 7, 5)
        numberObservable.subscribe(observerCreation())
        println();
        numberObservable.distinct()
                .subscribe(observerCreation())
    }

    fun take_takewhileOperator() {

        println("Take");
        val numberObservable = Observable.just(1, 2, 3, 4, 5, 7, 5)
        numberObservable.subscribe(observerCreation())
        println();
        numberObservable.take(3)
                .subscribe(observerCreation())

        println("\nTakeWhile");
        val numberObservable1 = Observable.just(1, 2, 3, 4, 5, 7, 5)
        numberObservable1.subscribe(observerCreation())
        println();
        numberObservable1.takeWhile { item: Int -> item <= 5 }
                .subscribe(observerCreation())
    }


    private fun observerCreation(): Observer<Int> {
        return object : Observer<Int> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(inetger: Int) {
                print("\t" + inetger)
            }

            override fun onError(e: Throwable) {}
            override fun onComplete() {}
        }
    }

    fun bufferOperator() {
        println("Buffer");
        val numberObservable = Observable.just(1, 2, 3, 4, 5, 7, 5)
                .buffer(3)
        numberObservable.subscribe(observerCreationList())
    }

    fun debounceOperator() {
        println("Debounce");
        val numberObservable = Observable.just(1, 2, 3, 4, 5, 7, 5)
                .debounce(10, TimeUnit.MICROSECONDS)
        numberObservable.subscribe(observerCreation())
    }

    fun map() {
        println("MAP");
        val numberObservable = Observable.just(1, 2, 3, 4, 5, 6)
                .map { item: Int -> item * 2 }
                .subscribe(observerCreation())
    }

    fun flatMap() {
        println("FlatMAP");
        val numberObservable1 = Observable.just(1)
        val numberObservable = Observable.just(1, 2, 3, 4, 5, 7, 5)
                .flatMap { item -> Observable.just<Int>(item + 10) }
                .subscribe(observerCreation())
    }

    fun concatMap() {
        val numberObservable = Observable.just(1, 2, 3, 4, 5, 6 )
                .switchMap{  item -> Observable.just<Int>(item + 10) }
        numberObservable   .subscribe(observerCreation())
    }

//    fun flatMap() {
//        val items: List<String> = Lists.newArrayList("a", "b", "c", "d", "e", "f")
//        val scheduler = TestScheduler()
//        Observable.from(items)
//                .flatMap({ item ->
//
//                    Observable.just<String>(s.toString() + "x")
//                            .delay(delay.toLong(), TimeUnit.SECONDS, scheduler)
//                })
//                .toList()
//                .doOnNext(System.out::println)
//                .subscribe()
//        scheduler.advanceTimeBy(1, TimeUnit.MINUTES)
//    }

    private fun observerCreationList(): Observer<List<Int>> {
        return object : Observer<List<Int>> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: List<Int>) {
                println(t)
            }

            override fun onError(e: Throwable) {
            }

            override fun onComplete() {
            }
        }
    }


}
