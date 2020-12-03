package com.zeynelerdi.mvpgithub.ui.repoDetail

class RepoDetailPresenter(val view: RepoDetailContract.View) : RepoDetailContract.Presenter {

    init {
        this.view.setPresenter(this)
    }

}