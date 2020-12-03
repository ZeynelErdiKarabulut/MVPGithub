package com.zeynelerdi.mvpgithub.room

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData


class GithubRepoRepository(application: Application) {
    private val repoDao: GithubRepoDAO
    private val allRepos: LiveData<List<GithubRepoEntity>>

    init {
        val database = AppDatabase(application)
        repoDao = database.repoDao()
        allRepos = repoDao.getAll()
    }

    fun insert(repo: GithubRepoEntity) {
        InsertNoteAsyncTask(repoDao).execute(repo)
    }

    fun getAll(): LiveData<List<GithubRepoEntity>> {
        return allRepos
    }

    private class InsertNoteAsyncTask  constructor(private val githubRepoDAO: GithubRepoDAO) : AsyncTask<GithubRepoEntity, Void, Void>() {

        override fun doInBackground(vararg notes: GithubRepoEntity): Void? {
            githubRepoDAO.insert(notes[0])
            return null
        }
    }
}