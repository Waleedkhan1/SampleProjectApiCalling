package com.evaluation.testproject.helpers


import android.content.Context
import android.widget.Toast

object CustomToast {

    private var context: Context? = null

    fun applicationReference(mContext: Context) {
        context = mContext
    }

    fun makeToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

}