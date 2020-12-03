package com.zeynelerdi.mvpgithub

import android.app.Application
import com.zeynelerdi.mvpgithub.api.APIInterface
import com.zeynelerdi.mvpgithub.api.GithubService

class App : Application() {

    private lateinit var apiInterface: APIInterface
    lateinit var githubService: GithubService
    override fun onCreate() {
        super.onCreate()
        instance = this
        initApi()
    }

    companion object {
        lateinit var instance: App
    }

    private fun initApi() {
        apiInterface = APIInterface.createRetrofit()
        githubService = GithubService(apiInterface)
    }
}