package com.mufeng.rxjavademo.rx

import android.util.Log
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import java.util.concurrent.TimeUnit

/**
 * 条件和布尔操作符
 */
class BooleanDemo {

    companion object {
        fun testAll(){
            Observable.just(1,2,3,4,5)
                    .all { it < 10 }
                    .subscribeBy(
                            onSuccess = {Log.e("TAG",it.toString())},
                            onError = {Log.e("TAG",it.localizedMessage)}
                    )
        }


        fun testContains(){

            Observable.just(1,3,444,50)
                    .contains(444)
                    .subscribeBy { Log.e("TAG",it.toString()) }

            Observable.just(1,3,444,50)
                    .isEmpty()
                    .subscribeBy { Log.e("TAG",it.toString()) }

        }

        fun testAmb(){
            Observable
                    .amb(
                            arrayListOf(
                                    //第一个Observable延迟1s后再发射
                                    Observable.just(1,2,3).delay(1,TimeUnit.SECONDS),
                                    Observable.just(4,5,6)
                            )
                    )
                    .subscribeBy { Log.e("TAG",it.toString()) }
        }

        fun testDefaultIfEmpty() {
            Observable.empty<Int>()
                    .defaultIfEmpty(666)
                    .subscribeBy { Log.e("TAG",it.toString())  }
        }

        fun testSequenceEqual() {
            Observable.sequenceEqual(
                    Observable.just(1,2,3,4,5),
                    Observable.just(1,2,3,4,5)
            ).subscribeBy { Log.e("TAG",it.toString())  }
        }

        fun testSkipUntil() {
            Observable.intervalRange(1,9,0,1,TimeUnit.SECONDS)
                    .skipUntil(Observable.timer(4,TimeUnit.SECONDS))
                    .subscribeBy { Log.e("TAG",it.toString()) }
        }

        fun testSkipWhile(){
            Observable.just(1,2,3,4,5,6)
                    .skipWhile { it < 3 }
                    .subscribeBy { Log.e("TAG",it.toString()) }
        }

        fun testTakeUntil(){
            Observable.just(1,2,3,4,5,6,7)
                    .takeUntil { it == 4 }
                    .subscribeBy { Log.e("TAG",it.toString()) }
        }

        fun testTakeWhile(){
            Observable.just(1,2,3,4,5,6,7)
                    .takeWhile { it < 5 }
                    .subscribeBy { Log.e("TAG",it.toString()) }
        }
    }

}