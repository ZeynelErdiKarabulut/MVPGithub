package com.zeynelerdi.mvpgithub.api.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubRepo(@field:SerializedName("full_name") val repoName: String
                      , @field:SerializedName("id") val repoID: Int
                      , @field:SerializedName("open_issues_count") val openIssuesCount: Int
                      , @field:SerializedName("stargazers_count") val starCount: Int
                      , @field:SerializedName("owner") val repoOwner: GithubRepoOwner
                      , var isFavourited: Boolean) : Parcelable