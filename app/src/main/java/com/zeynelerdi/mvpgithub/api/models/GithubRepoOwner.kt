package com.zeynelerdi.mvpgithub.api.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubRepoOwner (@field:SerializedName("avatar_url") val avatarUrl:String
                            , @field:SerializedName("login") val ownerName:String):Parcelable