package com.mufeng.rxjavademo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mufeng.rxjavademo.rx.CreateDemo
import com.mufeng.rxjavademo.rx.ObservableDemo

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun create(v: View){
        //Observable 生命周期
//        ObservableDemo.testDo()
        CreateDemo.testRepeatUntil();
    }

}
