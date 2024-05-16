package com.evaluation.testproject.repositories

import com.evaluation.testproject.listeners.RepositoryListener
import com.meplanet.application.listners.RetrofitCallingListener
import retrofit2.Response

open class BaseRepository(private val repositoryListener: RepositoryListener) :
    RetrofitCallingListener {

    override fun <T : Any> onSuccessResponse(key: String, response: Response<T>?) {
        if (response!!.isSuccessful) {
            repositoryListener.onSuccessResponse(key, response.body()!!)
        } else {
            repositoryListener.onFailureResponse(key, response.errorBody().toString())
        }
    }

    override fun onFailureResponse(key: String, error: String) {
        repositoryListener.onFailureResponse(key, error)
    }
}