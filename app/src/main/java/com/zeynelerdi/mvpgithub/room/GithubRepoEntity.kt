package com.zeynelerdi.mvpgithub.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "GithubRepoEntity")
data class GithubRepoEntity(@PrimaryKey @ColumnInfo(name = "repoID")val repoID: Int
                      , @ColumnInfo(name = "isFavourited") var isFavourited: Boolean)