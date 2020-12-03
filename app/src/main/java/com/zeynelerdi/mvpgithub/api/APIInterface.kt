package com.zeynelerdi.mvpgithub.api


import com.zeynelerdi.mvpgithub.api.models.GithubRepo
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit


interface APIInterface {
    companion object {

        fun createRetrofit(): APIInterface {
            val builder = OkHttpClient.Builder()
            builder.readTimeout(60, TimeUnit.SECONDS)
            builder.connectTimeout(60, TimeUnit.SECONDS)
            val client = builder.build()

            val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()

            return retrofit.create(APIInterface::class.java)

        }
    }

    @GET("users/{user}/repos")
    fun getRepoList(@Path("user") username: String): Call<List<GithubRepo>>

}