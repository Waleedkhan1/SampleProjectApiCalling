package com.evaluation.testproject.vmCallbacks

import android.os.Bundle
import androidx.fragment.app.Fragment

sealed class ActivityVMCallback {

    data class ShowProgressBar(var isVisible: Boolean) : ActivityVMCallback()
    object OnBackButtonPressed : ActivityVMCallback()
    object NoInternetAccess : ActivityVMCallback()
    data class OnActivityChanged<T>(var cls: Class<T>) : ActivityVMCallback()
    data class OnActivityChangedWithBundle<T>(var bundle: Bundle?, var cls: Class<T>) : ActivityVMCallback()
    data class OnActivityChangedWithNewTask<T>(var cls: Class<T>) : ActivityVMCallback()
    data class OnFragmentChanged(val fragment: Fragment) : ActivityVMCallback()

    companion object {

        fun showProgressBar(isVisible: Boolean): ActivityVMCallback = ShowProgressBar(isVisible)

        fun <T : Any> onActivityChanged(cls: Class<T>): ActivityVMCallback =
            OnActivityChanged(cls)

        fun <T : Any> onActivityChangedWithBundle(bundle: Bundle? = null, cls: Class<T>): ActivityVMCallback =
            OnActivityChangedWithBundle(bundle, cls)

        fun <T : Any> onActivityChangedWithNewTask(cls: Class<T>): ActivityVMCallback =
            OnActivityChangedWithNewTask(cls)

        fun onFragmentChanged(fragment: Fragment): ActivityVMCallback =
            OnFragmentChanged(fragment)

    }
}