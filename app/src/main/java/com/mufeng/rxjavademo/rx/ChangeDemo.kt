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
//        fun testMap() {
//            Observable.just("HELLO")
//                    .map {
//                        //把大写转换成小写
//                        it.toLowerCase()
//                    }
//                    .map {
//                        //添加新的字符串
//                        "$it MuFeng!"
//                    }
//                    .subscribeBy { Log.e("TAG", it) }
//        }

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
            Observable.range(0,100)
                    .groupBy {
                        if (it % 2 == 0)
                            "偶数组"
                        else
                            "奇数组"
                    }
                    .subscribe {
                        Log.e("TAG","GroupName: ${it.key}")
                        if (it.key == "偶数组") {
                            it.subscribeBy { Log.e("TAG","偶数: $it") }
                        }
                    }
        }

    }
    data class User(var name: String, var languages: ArrayList<Language>)

    data class Language(var name: String)

}

