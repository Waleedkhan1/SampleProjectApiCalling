package com.evaluation.testproject.viewModels.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.evaluation.testproject.helpers.CustomToast
import com.evaluation.testproject.listeners.RepositoryListener
import com.evaluation.testproject.viewModels.base.BaseActivityVM
import com.evaluation.testproject.vmCallbacks.ActivityVMCallback
import com.evaluation.testproject.vmCallbacks.category.MainCallBack

class DashboardVM : BaseActivityVM(), RepositoryListener {
    private val TAG = DashboardVM::class.java.simpleName

    var isBackBtnActive = false

    private val callback: MutableLiveData<MainCallBack> by lazy {
        MutableLiveData<MainCallBack>()
    }

    fun getCallback(): LiveData<MainCallBack> {
        return callback
    }


    override fun <T : Any> onSuccessResponse(key: String, result: T) {

    }

    override fun onFailureResponse(key: String, error: String) {
//        Logger.e(TAG, error)
        vmActivityCB.value = ActivityVMCallback.showProgressBar(false)
        CustomToast.makeToast(error)
    }

}