package com.zeynelerdi.mvpgithub.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GithubRepoDAO {
    @Query("SELECT * FROM githubrepoentity")
    fun getAll(): LiveData<List<GithubRepoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(githubRepoEntity: GithubRepoEntity)

}