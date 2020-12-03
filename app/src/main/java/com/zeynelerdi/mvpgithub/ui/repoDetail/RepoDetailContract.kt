package com.zeynelerdi.mvpgithub.ui.repoDetail

import com.zeynelerdi.mvpgithub.ui.base.BasePresenter
import com.zeynelerdi.mvpgithub.ui.base.BaseView


interface RepoDetailContract{
    interface View: BaseView<Presenter>
    interface Presenter: BasePresenter
}