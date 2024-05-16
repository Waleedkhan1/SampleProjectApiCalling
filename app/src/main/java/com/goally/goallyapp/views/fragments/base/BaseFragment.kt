package com.evaluation.testproject.views.fragments.base

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.evaluation.testproject.helpers.CustomToast
import com.evaluation.testproject.listeners.HomeListener
import com.evaluation.testproject.vmCallbacks.FragmentVMCallback
import com.goally.goallyapp.R

open class BaseFragment : Fragment() {
    protected lateinit var mContext: Context
    protected lateinit var homeListener: HomeListener
    protected var mView: View? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        this.homeListener = context as HomeListener
    }

    protected val vmCallbackObserver = Observer<FragmentVMCallback> { callBack ->

        when (callBack) {

//            ProgressBar Visibility Handing
            is FragmentVMCallback.ShowProgressBar -> {
                homeListener.onProgressVisibility((callBack.isVisible))
            }

            is FragmentVMCallback.OnBackButtonPressed -> {
                homeListener.onBackButtonPressed()
            }

            is FragmentVMCallback.OnFragmentChanged -> {
                homeListener.onFragmentChangeListener(callBack.fragment)
            }

            is FragmentVMCallback.NoInternetAccess -> {
                CustomToast.makeToast(getString(R.string.string_internet_connection_not_stable))
            }
//            is FragmentVMCallback.HideSoftKeyboard -> {
//                Utils.hideKeyboard(mContext, mView)
//            }

            is FragmentVMCallback.OnFragmentChangedWithPopUp -> {
                homeListener.onFragmentChangeListener(callBack.popFragment, callBack.fragment)
            }
        }
    }

//    protected fun clearArguments() {
//        this.arguments?.clear()
//    }
}