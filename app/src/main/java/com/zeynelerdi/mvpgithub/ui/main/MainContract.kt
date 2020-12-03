package com.zeynelerdi.mvpgithub.ui.main

import com.zeynelerdi.mvpgithub.api.models.GithubRepo
import com.zeynelerdi.mvpgithub.ui.base.BasePresenter
import com.zeynelerdi.mvpgithub.ui.base.BaseView

interface MainContract{
    interface View: BaseView<Presenter> {
        fun getRepoListSuccess(repoList: List<GithubRepo>)
        fun getRepoListFail(error: String)
        fun showLoading(isStarted:Boolean)
    }
    interface Presenter: BasePresenter {
        fun getRepoList(username: String)
    }
}