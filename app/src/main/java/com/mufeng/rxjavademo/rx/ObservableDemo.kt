package com.mufeng.rxjavademo.rx

import android.util.Log
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy

class ObservableDemo {

    companion object {
        /**
         * 测试do操作符, 以及Observable的生命周期
         */
        fun testDo() {
            Observable.just("Hello RxJava!")
                    .doOnNext { Log.e("TAG", "doOnNext: $it") }
                    .doAfterNext { Log.e("TAG", "doAfterNext: $it") }
                    .doAfterTerminate { Log.e("TAG", "doAfterTerminate: ") }
                    .doFinally { Log.e("TAG", "doFinally: ") }
                    .doOnComplete { Log.e("TAG", "doOnComplete: ") }
                    .doOnDispose { Log.e("TAG", "doOnDispose: ") }
                    .doOnEach { Log.e("TAG", "doOnEach: ${if (it.isOnNext) "onNext" else if (it.isOnComplete) "onComplete" else "onError"}") }
                    .doOnError { Log.e("TAG", "doOnError: ${it.message}") }
                    .doOnLifecycle({Log.e("TAG", "doOnLifecycle: ${it.isDisposed}")},{Log.e("TAG", "doOnLifecycle run: ")})
                    .doOnSubscribe { Log.e("TAG", "doOnSubscribe: ${it.isDisposed}") }
                    .doOnTerminate { Log.e("TAG", "doOnTerminate: ") }
                    .subscribeBy(
                            onNext = {Log.e("TAG", "onNext: $it")},
                            onComplete = {Log.e("TAG", "onComplete: ")}
                    )

        }
    }

}