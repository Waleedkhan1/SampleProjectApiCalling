package com.evaluation.testproject.viewModels.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.evaluation.testproject.helpers.CustomToast
import com.evaluation.testproject.listeners.RepositoryListener
import com.evaluation.testproject.viewModels.MainVM
import com.evaluation.testproject.vmCallbacks.ActivityVMCallback


open class BaseActivityVM : ViewModel() ,RepositoryListener {
    /*Start Activity Elements*/
    private val TAG = MainVM::class.java.simpleName

    protected val vmActivityCB: MutableLiveData<ActivityVMCallback> by lazy {
        MutableLiveData<ActivityVMCallback>()
    }

    fun getActivityCB(): LiveData<ActivityVMCallback> {
        return vmActivityCB
    }

    override fun <T : Any> onSuccessResponse(key: String, result: T) {
        vmActivityCB.value = ActivityVMCallback.showProgressBar(false)
    }

    override fun onFailureResponse(key: String, error: String) {
//        Logger.e(TAG, error)
        vmActivityCB.value = ActivityVMCallback.showProgressBar(false)
        CustomToast.makeToast(error)
    }
}