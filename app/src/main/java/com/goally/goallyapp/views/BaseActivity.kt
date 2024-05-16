package com.evaluation.testproject.views

import android.content.Intent
import android.os.Build
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.evaluation.testproject.helpers.CustomToast
import com.evaluation.testproject.helpers.ProgressDialogue
import com.evaluation.testproject.vmCallbacks.ActivityVMCallback
import com.goally.goallyapp.R


open class BaseActivity : AppCompatActivity() {
    var mIntent: Intent? = null
    var fragmentName : String = ""

    private val progressDialogue by lazy {
        ProgressDialogue(this)
    }

    fun progressDialogueVisibility(isVisible: Boolean) {
        if (isVisible)
            progressDialogue.show()
        else
            progressDialogue.dismiss()
    }

    protected val vmCallbackObserver = Observer<ActivityVMCallback> { callBack ->
        mIntent = null

        when (callBack) {

            //ProgressBar Visibility Handing
            is ActivityVMCallback.ShowProgressBar -> {
                progressDialogueVisibility((callBack.isVisible))
            }

            is ActivityVMCallback.OnBackButtonPressed -> {
                onBackPressed()
            }

            is ActivityVMCallback.NoInternetAccess -> {
                CustomToast.makeToast("internet")
            }

            is ActivityVMCallback.OnActivityChanged<*> -> {
                mIntent = Intent(this, callBack.cls)
            }

            is ActivityVMCallback.OnActivityChangedWithBundle<*> -> {
                mIntent = Intent(this, callBack.cls)
                mIntent!!.putExtra("data", callBack.bundle)
            }

            is ActivityVMCallback.OnActivityChangedWithNewTask<*> -> {
                mIntent = Intent(this, callBack.cls)
                mIntent!!.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }

            is ActivityVMCallback.OnFragmentChanged -> {
            }
        }

        if (mIntent != null) {
            startActivity(mIntent)
        }
    }

    protected fun onFragmentChange(fragment: Fragment?) {
        if (fragment != null) {
            val backStateName: String = fragment::class.java.name
            val manager: FragmentManager = supportFragmentManager
            val fragmentPopped: Boolean = manager.popBackStackImmediate(
                backStateName,
                0
            )
            fragmentName = backStateName
            if (!fragmentPopped) {
                val fragmentTransaction: FragmentTransaction = manager.beginTransaction()
                fragmentTransaction.replace(R.id.frameLayout, fragment, backStateName)
                fragmentTransaction.addToBackStack(backStateName)
                fragmentTransaction.commit()
            }
        }
    }


    protected fun onFragmentChange1(fragment: Fragment?, bool: Boolean) {
        if (fragment != null) {
            val backStateName: String = fragment::class.java.name
            val manager: FragmentManager = supportFragmentManager
            val fragmentTransaction: FragmentTransaction = manager.beginTransaction()
            fragmentTransaction.add(R.id.frameLayout, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }

    protected fun onFragmentChangeWithPop(popFragment: String, fragment: Fragment?) {
        if (fragment != null) {
            val backStateName: String = fragment::class.java.name
            val manager: FragmentManager = supportFragmentManager

            manager.popBackStack(
                popFragment,
                0
            )

            val fragmentTransaction: FragmentTransaction = manager.beginTransaction()

            fragmentTransaction.replace(R.id.frameLayout, fragment, backStateName)
            fragmentTransaction.addToBackStack(backStateName)
            fragmentTransaction.commit()
        }
    }


    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//            window.decorView.layoutDirection =
//                if (Constants.ARABIC == getLanguage(this)) View.LAYOUT_DIRECTION_RTL else View.LAYOUT_DIRECTION_LTR
        }

        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

}