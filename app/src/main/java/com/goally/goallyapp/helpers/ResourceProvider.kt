package com.evaluation.testproject.helpers

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat

object ResourceProvider {

    private lateinit var mContext: Context

    fun setupRP(mContext: Context) {
        this.mContext = mContext
    }

    fun getString(resId: Int): String {
        return mContext.getString(resId)
    }

    fun getString(resId: Int, value: String?): String? {
        return mContext.getString(resId, value)
    }

    fun getStringArray(resId: Int): Array<String> {
        return mContext.resources.getStringArray(resId)
    }

    fun getColor(resId: Int): String {
        return mContext.resources.getString(resId)
    }


    fun getDrawable(resId: Int): Drawable {

      return ContextCompat.getDrawable(mContext, resId)!!
    }
}