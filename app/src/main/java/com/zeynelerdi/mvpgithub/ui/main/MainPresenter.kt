package com.zeynelerdi.mvpgithub.ui.main

import com.zeynelerdi.mvpgithub.App
import com.zeynelerdi.mvpgithub.api.GithubService
import com.zeynelerdi.mvpgithub.api.models.GithubRepo

class MainPresenter(val view: MainContract.View) : MainContract.Presenter {
    init {
        this.view.setPresenter(this)
    }

    override fun getRepoList(username: String) {
        App.instance.githubService.getRepoList(username, object : GithubService.ServiceCallback<List<GithubRepo>> {
            override fun onSuccess(response: List<GithubRepo>?) {
                if (response != null && response.isNotEmpty()) {
                    view.getRepoListSuccess(response)
                } else {
                    view.getRepoListFail("")
                }
            }

            override fun onFail(error: String) {
                view.getRepoListFail(error)
            }

            override fun onShowLoading() {
                view.showLoading(true)
            }

            override fun onHideLoading() {

            }
        })
    }
}