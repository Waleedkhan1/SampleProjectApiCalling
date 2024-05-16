package com.evaluation.testproject.network

import com.evaluation.testproject.helpers.CustomToast
import com.evaluation.testproject.helpers.ResourceProvider
import com.goally.goallyapp.R
import com.meplanet.application.listners.RetrofitCallingListener
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitCalling<T : Any>(
    var retrofitCallingListener: RetrofitCallingListener,
    var key: String,
    var call: Call<T>
) {

    var job: CompletableJob = Job()

    fun apiCalling() {
        CoroutineScope(Dispatchers.IO + job).launch {

            call.enqueue(object : Callback<T> {

                override fun onResponse(
                    call: Call<T>?,
                    response: Response<T>?
                ) {

                    job.complete()
                    retrofitCallingListener.onSuccessResponse(key, response)
                }

                override fun onFailure(call: Call<T>?, t: Throwable?) {
//                    Logger.e("RetrofitCalling", t.toString())
                    job.complete()
                    retrofitCallingListener.onFailureResponse(
                        key,
                        ResourceProvider.getString(R.string.string_internet_connection_not_stable)
                    )
                }
            })
        }
    }
}