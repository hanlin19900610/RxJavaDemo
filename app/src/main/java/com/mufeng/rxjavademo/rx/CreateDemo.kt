package com.mufeng.rxjavademo.rx

import android.util.Log
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class CreateDemo {

    companion object {
        /**
         * create() 操作符
         */
        fun testCreate() {
            Observable.create<Int> {
                if (!it.isDisposed) {
                    it.onNext(1)
                    it.onNext(2)
                    it.onNext(3)
                    it.onComplete()
                }
            }.subscribeBy(
                    onNext = { Log.e("TAG", "onNext: ${it.toString()}") },
                    onComplete = { Log.e("TAG", "onComplete") }
            )
        }

        /**
         * just() 操作符
         */
        fun testJust() {
            Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                    .subscribeBy(
                            onNext = { Log.e("TAG", "onNext: ${it.toString()}") },
                            onComplete = { Log.e("TAG", "onComplete") }
                    )
        }

        fun testFrom() {
            val list = arrayListOf<Int>(1, 2, 3, 4, 5)
            list.toObservable()
                    .subscribeBy(
                            onNext = { Log.e("TAG", "onNext: ${it.toString()}") },
                            onComplete = { Log.e("TAG", "onComplete") }
                    )
        }

        fun testFromFuture() {
            val executorService = Executors.newSingleThreadExecutor();
            val future = executorService.submit(MyCallable())
            Observable.fromFuture(future, 3, TimeUnit.SECONDS)
                    .subscribeBy(
                            onNext = { Log.e("TAG", "onNext: $it") },
                            onError = { it.printStackTrace() }
                    )
        }

        fun testRepeat(){
            Observable.just("hello")
//                    .repeat()
                    .repeat(3)
                    .subscribeBy(
                            onNext = { Log.e("TAG", "onNext: ${it.toString()}") },
                            onComplete = { Log.e("TAG", "onComplete") },
                            onError = { it.printStackTrace() }
                    )
        }

        fun testRepeatWhen(){
            Observable.range(0,5)
                    .repeatWhen {
                        Observable.timer(10,TimeUnit.SECONDS)
                    }
                    .subscribeBy(
                            onNext = { Log.e("TAG", "onNext: ${it.toString()}") },
                            onComplete = { Log.e("TAG", "onComplete") },
                            onError = { it.printStackTrace() }
                    )
        }

        fun testRepeatUntil(){
            val time = System.currentTimeMillis();
            Observable.just("hello")
                    .repeatUntil {
                        System.currentTimeMillis() - time > 5000
                    }
                    .subscribeBy(
                            onNext = { Log.e("TAG", "onNext: ${it.toString()}") },
                            onComplete = { Log.e("TAG", "onComplete") },
                            onError = { it.printStackTrace() }
                    )
        }
    }

    class MyCallable : Callable<String> {
        override fun call(): String {
            Log.e("TAG", "模拟一些耗时操作...")
            Thread.sleep(5000)
            return "OK"
        }
    }

}