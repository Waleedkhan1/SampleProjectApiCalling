package com.evaluation.testproject.network


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitController {

    private val client: OkHttpClient by lazy {
        val interceptor = HttpLoggingInterceptor()
//        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
//        } else {
//            interceptor.level = HttpLoggingInterceptor.Level.NONE
//        }

        OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor(RetrofitInterceptor())
            .build()
    }

    private val retrofitController: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(APILinks.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())

//            .baseUrl(BuildConfig.BASE_URL)
    }

    val apiService: ApiService by lazy {
        retrofitController
            .build()
            .create(ApiService::class.java)
    }
}