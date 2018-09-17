package com.mufeng.rxjavademo.rx

import android.util.Log
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy

class FilterDemo {

    companion object {

        fun testFirst(){
            Observable.just(1,2,3,4)
                    .first(1)
                    .subscribeBy(
                            onSuccess = {Log.e("TAG",it.toString())},
                            onError = {Log.e("TAG",it.localizedMessage)}
                    )

            // Observable 不发射任何数据,那么first操作符的默认值就起作用了
            Observable.empty<Int>()
                    .first(2)
                    .subscribeBy(
                            onSuccess = {Log.e("TAG","显示默认值: "+it.toString())},
                            onError = {Log.e("TAG",it.localizedMessage)}
                    )
        }

        fun testLast(){

            Observable.just(1,2,3,4)
                    .last(3)
                    .subscribeBy(
                            onSuccess = {Log.e("TAG",it.toString())},
                            onError = {Log.e("TAG",it.localizedMessage)}
                    )
            // Observable 不发射任何数据,那么last操作符的默认值就起作用了
            Observable.empty<Int>()
                    .last(2)
                    .subscribeBy(
                            onSuccess = {Log.e("TAG","显示默认值: "+it.toString())},
                            onError = {Log.e("TAG",it.localizedMessage)}
                    )
        }

        fun testTake(){
            Observable.just(1,2,3,4,5)
                    .take(7)
                    .subscribeBy(
                            onNext = {Log.e("TAG",it.toString())},
                            onError = {Log.e("TAG",it.localizedMessage)},
                            onComplete = {Log.e("TAG","onComplete")}
                    )
        }

        fun testTakeLast(){
            Observable.just(1,2,3,4,5,6)
                    .takeLast(3)
                    .subscribeBy(
                            onNext = {Log.e("TAG",it.toString())},
                            onError = {Log.e("TAG",it.localizedMessage)},
                            onComplete = {Log.e("TAG","onComplete")}
                    )
        }

        fun testSkip(){
            Observable.just(1,2,3,4,5,6)
                    .skip(3)
                    .subscribeBy(
                            onNext = {Log.e("TAG",it.toString())},
                            onError = {Log.e("TAG",it.localizedMessage)},
                            onComplete = {Log.e("TAG","onComplete")}
                    )
        }

        fun testSkipLast(){
            Observable.just(1,2,3,4,5,6)
                    .skipLast(3)
                    .subscribeBy(
                            onNext = {Log.e("TAG",it.toString())},
                            onError = {Log.e("TAG",it.localizedMessage)},
                            onComplete = {Log.e("TAG","onComplete")}
                    )
        }

        fun testElementAt(){
            Observable.just(1,2,3,4,5,6)
                    .elementAt(-1)
                    .subscribeBy(
                            onSuccess = {Log.e("TAG",it.toString())},
                            onError = {Log.e("TAG",it.localizedMessage)},
                            onComplete = {Log.e("TAG","onComplete")}
                    )
        }

        fun testDistince(){
            Observable.just(1,2,1,2,3,1)
                    .distinct()
                    .subscribeBy(
                            onNext = {Log.e("TAG",it.toString())},
                            onError = {Log.e("TAG",it.localizedMessage)},
                            onComplete = {Log.e("TAG","onComplete")}
                    )
        }

        fun testFilter(){
            Observable.just(1,2,3,4,5,6)
                    .filter{it > 3}
                    .subscribeBy(
                            onNext = {Log.e("TAG",it.toString())},
                            onError = {Log.e("TAG",it.localizedMessage)},
                            onComplete = {Log.e("TAG","onComplete")}
                    )
        }

    }

}