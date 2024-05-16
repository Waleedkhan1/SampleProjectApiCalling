package com.evaluation.testproject.models.category

import com.evaluation.testproject.models.BaseResponse1
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ApiResponseModel : BaseResponse1<ApiResponseModel.Results>(){

    class Results(
        var adult: Boolean,
        var backdrop_path: String,
        var genre_ids: List<Int>,
        var id: Int,
        var origin_country: List<String>,
        var original_language: String,
        var original_name: String,
        var overview: String,
        var popularity: Float,
        var poster_path: String,
        var first_air_date: String,
        var name: String,
        var vote_average: Float,
        var vote_count: Int
    )
}