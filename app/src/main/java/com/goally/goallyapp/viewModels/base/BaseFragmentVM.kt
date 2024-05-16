package com.evaluation.testproject.viewModels.base


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.evaluation.testproject.helpers.CustomToast
import com.evaluation.testproject.listeners.RepositoryListener
import com.evaluation.testproject.viewModels.MainVM
import com.evaluation.testproject.vmCallbacks.ActivityVMCallback
import com.evaluation.testproject.vmCallbacks.FragmentVMCallback

open class BaseFragmentVM : ViewModel(), RepositoryListener {

    /*Start Fragment Elements*/
    protected val vmFragmentCB: MutableLiveData<FragmentVMCallback> by lazy {
        MutableLiveData<FragmentVMCallback>()
    }

    fun getFragmentCB(): LiveData<FragmentVMCallback> {
        return vmFragmentCB
    }

    fun clearObservers(vmObserver : Observer<FragmentVMCallback>) {
        vmFragmentCB.removeObserver(vmObserver)
        vmFragmentCB.value = FragmentVMCallback.showProgressBar(false)
    }

    override fun <T : Any> onSuccessResponse(key: String, result: T) {
        vmFragmentCB.value = FragmentVMCallback.showProgressBar(false)
    }

    override fun onFailureResponse(key: String, error: String) {
        vmFragmentCB.value = FragmentVMCallback.showProgressBar(false)
        CustomToast.makeToast(error)
    }
    /*End Fragment Elements*/
}