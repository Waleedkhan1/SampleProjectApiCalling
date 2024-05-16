package com.evaluation.testproject.repositories

import com.evaluation.testproject.listeners.RepositoryListener
import com.evaluation.testproject.network.APIKeys
import com.evaluation.testproject.network.RetrofitCalling
import com.evaluation.testproject.network.RetrofitController

class HomeRepository(private val repositoryListener: RepositoryListener) :
    BaseRepository(repositoryListener) {

    fun callApi(key: String,page: Int) {
        val uniqueKey = APIKeys.ApiKey
        val apiService = RetrofitController.apiService.getGames(key,page)
        val retrofitCalling = RetrofitCalling(this, uniqueKey, apiService)
        retrofitCalling.apiCalling()
    }

}