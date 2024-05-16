package com.evaluation.testproject.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.evaluation.testproject.helpers.ResourceProvider
import com.evaluation.testproject.helpers.Utils
import com.evaluation.testproject.listeners.RepositoryListener
import com.evaluation.testproject.models.category.ApiResponseModel
import com.evaluation.testproject.network.APIKeys
import com.evaluation.testproject.repositories.HomeRepository
import com.evaluation.testproject.viewModels.base.BaseFragmentVM
import com.evaluation.testproject.vmCallbacks.FragmentVMCallback
import com.evaluation.testproject.vmCallbacks.category.MainCallBack
import com.goally.goallyapp.R

class MainVM : BaseFragmentVM(), RepositoryListener {
    private val TAG = MainVM::class.java.simpleName

    private val homeRepository = HomeRepository(this)
    var loadingPagination: Boolean = false
    var pageNumber: Int = 1

    private val callback: MutableLiveData<MainCallBack> by lazy {
        MutableLiveData<MainCallBack>()
    }

    val topResponse: MutableLiveData<ApiResponseModel> by lazy {
        MutableLiveData<ApiResponseModel>()
    }


    fun getAPi() {
        if (Utils.isNetworkAvailable()) {
            vmFragmentCB.value = FragmentVMCallback.showProgressBar(true)
            homeRepository.callApi(ResourceProvider.getString(R.string.API_KEY), 4)
        }
    }

    fun getCallback(): LiveData<MainCallBack> {
        return callback
    }

    override fun <T : Any> onSuccessResponse(key: String, result: T) {
        super.onSuccessResponse(key, result)
        when (key) {
            APIKeys.ApiKey -> {
                getImagesesponse(result as ApiResponseModel)
            }
        }
    }

    private fun getImagesesponse(apiResponse: ApiResponseModel) {

        if (apiResponse.results?.size!! > 0) {
            this.topResponse.value = apiResponse
            callback.value = MainCallBack.PopulateData
        }

    }

    fun remove() {
        callback.value = null
    }
}