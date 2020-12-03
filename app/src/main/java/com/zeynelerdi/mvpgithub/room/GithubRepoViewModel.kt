package com.zeynelerdi.mvpgithub.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class GithubRepoViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: GithubRepoRepository = GithubRepoRepository(application)
    private val allRepos: LiveData<List<GithubRepoEntity>>

    init {
        allRepos = repository.getAll()
    }

    fun insert(repo: GithubRepoEntity) {
        repository.insert(repo)
    }

    fun getAllRepos(): LiveData<List<GithubRepoEntity>> {
        return allRepos
    }
}