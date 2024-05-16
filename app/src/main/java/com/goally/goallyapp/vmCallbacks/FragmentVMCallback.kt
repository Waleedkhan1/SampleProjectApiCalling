package com.evaluation.testproject.vmCallbacks

import androidx.fragment.app.Fragment

sealed class FragmentVMCallback {
    data class ShowProgressBar(var isVisible: Boolean) : FragmentVMCallback()
    data class OnFragmentChanged(val fragment: Fragment) : FragmentVMCallback()

    data class OnFragmentChangedWithPopUp(val popFragment: String, val fragment: Fragment) :
        FragmentVMCallback()

    object OnBackButtonPressed : FragmentVMCallback()
    object NoInternetAccess : FragmentVMCallback()

    companion object {
        fun showProgressBar(isVisible: Boolean): FragmentVMCallback = ShowProgressBar(isVisible)
        fun onFragmentChanged(fragment: Fragment): FragmentVMCallback = OnFragmentChanged(fragment)

        fun onFragmentChangedWithPopUp(
            popFragment: String,
            fragment: Fragment
        ): FragmentVMCallback = OnFragmentChangedWithPopUp(popFragment, fragment)
    }
}