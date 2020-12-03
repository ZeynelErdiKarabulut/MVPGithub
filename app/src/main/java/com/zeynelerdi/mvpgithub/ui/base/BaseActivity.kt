package com.zeynelerdi.mvpgithub.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    lateinit var baseActivity: BaseActivity
    abstract fun getLayoutId(): Int
    abstract fun bindView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseActivity = this
        setContentView(getLayoutId())
        bindView()
    }
}
