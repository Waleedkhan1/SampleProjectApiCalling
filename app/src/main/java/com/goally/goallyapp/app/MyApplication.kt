package com.evaluation.testproject.app

import android.app.Application
import android.content.Context
import com.evaluation.testproject.helpers.CustomToast
import com.evaluation.testproject.helpers.ResourceProvider
import com.evaluation.testproject.helpers.Utils
import java.util.*


class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val context: Context = applicationContext()
        ResourceProvider.setupRP(this)
        Utils.applicationReference(this)
        CustomToast.applicationReference(this)
    }

    init {
        instance = this
    }

    companion object {
        private var instance: MyApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }
}