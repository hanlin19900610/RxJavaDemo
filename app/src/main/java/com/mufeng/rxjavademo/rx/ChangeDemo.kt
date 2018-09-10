package com.mufeng.rxjavademo.rx

import android.util.Log
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable
import org.intellij.lang.annotations.Language

/**
 * 变换操作符
 */
class ChangeDemo {
    companion object {
        fun testMap() {
            Observable.just("HELLO")
                    .map {
                        //把大写转换成小写
                        it.toLowerCase()
                    }
                    .map {
                        //添加新的字符串
                        "$it MuFeng!"
                    }
                    .subscribeBy { Log.e("TAG", it) }
        }

        fun testFlatMap() {

            val language1 = Language("Android")
            val language2 = Language("Java")
            val language3 = Language("Kotlin")
            val language4 = Language("Python")

            val list = arrayListOf<Language>()
            list.add(language1)
            list.add(language2)
            list.add(language3)
            list.add(language4)

            val user = User("MuFeng", list)

            //map操作符实现
            Observable.just(user)
                    .map { user.languages }
                    .subscribeBy {
                        for (l in it) {
                            Log.e("TAG", l.name)
                        }
                    }

            Log.e("TAG", "----------------------------------")

            //flatMap操作符实现
            Observable.just(user)
                    .flatMap { user.languages.toObservable() }
                    .subscribeBy {
                        Log.e("TAG", it.name)
                    }

        }

        fun testGroupBy(){
            Observable.range(0,51)
                    .groupBy {
                        if (it % 2 == 0)
                            "偶数组"
                        else
                            "奇数组"
                    }
                    .subscribe {
                        //打印分组名称
                        Log.e("TAG","GroupName: ${it.key}")
                        //打印出所有偶数
                        if (it.key == "偶数组") {
                            it.subscribeBy { Log.e("TAG","偶数: $it") }
                        }
                    }
        }

        fun testBuffer(){
            Observable.range(1,10)
                    .buffer(5,1)
                    .subscribeBy(
                            onNext = {Log.e("TAG",it.toString())},
                            onComplete = {Log.e("TAG","onComplete")}
                    )
        }

        fun testBufferWithError(){
            Observable.create<Int> {
                it.onNext(1)
                it.onNext(2)
                it.onNext(3)
                it.onNext(4)
                it.onNext(5)
                it.onNext(6)
                it.onError(Throwable("error"))
                it.onNext(7)
                it.onNext(8)
                it.onNext(9)
                it.onNext(10)
            }.buffer(2)
                    .subscribeBy(
                            onNext = {Log.e("TAG",it.toString())},
                            onComplete = {Log.e("TAG","onComplete")},
                            onError = {Log.e("TAG","onError: ${it.message}")}
                    )
        }

        fun testWindow(){
            Observable.range(1,10)
                    .window(2)
                    .subscribeBy(
                            onNext = {
                                Log.e("TAG","onNext: ")
                                it.subscribeBy { Log.e("TAG","onNext: $it") }
                            },
                            onComplete = {Log.e("TAG","onComplete")}
                    )
        }

    }
    data class User(var name: String, var languages: ArrayList<Language>)

    data class Language(var name: String)

}

