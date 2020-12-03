package com.zeynelerdi.mvpgithub.ui.base

interface BaseView<T> {
    val baseActivity: BaseActivity
    fun setPresenter(presenter: T)
}