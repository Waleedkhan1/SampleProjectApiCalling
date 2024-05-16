package com.meplanet.application.listners

import retrofit2.Response

interface RetrofitCallingListener {

    fun <T : Any> onSuccessResponse(key: String, response: Response<T>?)
    fun onFailureResponse(key: String, error: String)
}