package com.evaluation.testproject.network

import okhttp3.*

class RetrofitInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
//            .addHeader("Authorization", "Bearer ${SharedPref.instance[SharedPref.TOKEN, ""]}")
            .addHeader("Accept", "application/json")
//            .addHeader("lang",  SharedPref.instance.getString(SharedPref.LANGUAGE, Constants.ENGLISH).toString())
            .build()
        return chain.proceed(request)
    }
}