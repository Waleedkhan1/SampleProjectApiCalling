package com.evaluation.testproject.listeners

import android.view.View
import androidx.fragment.app.Fragment

interface HomeListener {

    fun onHomeDataChangeListener(
        toolBarVisibility: Boolean,
        leftBtnVisibility: Boolean,
        rightBtnVisibility: Boolean,
        leftDrawable: Int,
        rightDrawable: Int,
        newTitle: String,
        bg: Int? = null,
        imcLogoVisibility: Int = View.INVISIBLE,
        imcTopCurveVisibility: Int = View.INVISIBLE,
    )

    fun onFragmentChangeListener(fragment: Fragment?)

    fun onFragmentChangeListener(fragment: Fragment?,boolean: Boolean)

    fun onFragmentChangeListener(popFragment: String, fragment: Fragment?)

    fun onBackButtonPressed()

    fun onProgressVisibility(isVisible: Boolean)

}