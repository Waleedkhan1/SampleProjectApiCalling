package com.evaluation.testproject.network

import com.evaluation.testproject.models.category.ApiResponseModel
import retrofit2.Call
import retrofit2.http.*


interface ApiService {

    //Images
    @GET(APILinks.API_ENDPOINT)
    fun getGames(  @Query("api_key") api_key: String,
                   @Query("page") page: Int): Call<ApiResponseModel>

}
