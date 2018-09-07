package com.mufeng.rxjavademo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mufeng.rxjavademo.rx.ChangeDemo
import com.mufeng.rxjavademo.rx.CreateDemo
import com.mufeng.rxjavademo.rx.ObservableDemo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_create.setOnClickListener {
            ChangeDemo.testGroupBy()
        }
    }

}
