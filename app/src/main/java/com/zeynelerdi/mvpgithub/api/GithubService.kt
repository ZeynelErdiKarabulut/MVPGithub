package com.zeynelerdi.mvpgithub.api

import com.zeynelerdi.mvpgithub.api.models.GithubRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubService(private val api: APIInterface) {

    private fun <T> getResponse( bodyCall: Call<T>, serviceCallback: ServiceCallback<T>) {
        try {
            serviceCallback.onShowLoading()
            val callback = object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    try {
                        serviceCallback.onHideLoading()
                        if (response.isSuccessful && response.body() != null) {
                            serviceCallback.onSuccess(response.body())
                        } else {
                            serviceCallback.onFail("")
                        }
                    } catch (e: Exception) {
                        serviceCallback.onFail(e.localizedMessage)
                    }
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    serviceCallback.onHideLoading()
                    serviceCallback.onFail(t.localizedMessage)
                }
            }
            bodyCall.enqueue(callback)
        } catch (e: Exception) {
            serviceCallback.onFail(e.localizedMessage)
        }
    }

    fun getRepoList(username: String, serviceCallback: ServiceCallback<List<GithubRepo>>) {
        val bodyCall = api.getRepoList(username)
        getResponse(bodyCall, serviceCallback)
    }

    interface ServiceCallback<T> {
        fun onSuccess(response: T?)

        fun onFail(error:String)

        fun onShowLoading()

        fun onHideLoading()
    }
}