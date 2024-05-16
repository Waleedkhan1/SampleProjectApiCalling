package com.evaluation.testproject.views

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.evaluation.testproject.helpers.Utils
import com.evaluation.testproject.listeners.HomeListener
import com.evaluation.testproject.viewModels.dashboard.DashboardVM
import com.evaluation.testproject.views.fragments.HomeFragment
import com.evaluation.testproject.vmCallbacks.category.MainCallBack
import com.goally.goallyapp.R
import com.goally.goallyapp.databinding.ActivityMainBinding


class MainActivity : BaseActivity(), HomeListener {

    private val mainVM: DashboardVM by lazy { ViewModelProvider(this).get(DashboardVM::class.java) }

    private lateinit var binding: ActivityMainBinding

    private var doubleBackToExitCheck = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
                .apply {
                    this.lifecycleOwner = this@MainActivity
                    this.viewModel = mainVM
                }

        setContentView(binding.root)

        init()

    }

    private fun init() {
        mainVM.getActivityCB().observe(this, vmCallbackObserver)
        mainVM.getCallback().observe(this, callbackObserver)
        onFragmentChangeListener(HomeFragment())
    }

    private val callbackObserver = Observer<MainCallBack> { callBack ->
        when (callBack) {
            MainCallBack.PopulateData -> {
                populateData()
            }

            else -> {

            }
        }
    }

    private fun populateData() {

    }

    override fun onHomeDataChangeListener(
        toolBarVisibility: Boolean,
        leftBtnVisibility: Boolean,
        rightBtnVisibility: Boolean,
        leftDrawable: Int,
        rightDrawable: Int,
        newTitle: String,
        bg: Int?,
        imcLogoVisibility: Int,
        imcTopCurveVisibility: Int,
    ) {

    }

    override fun onFragmentChangeListener(fragment: Fragment?) {
        onFragmentChange(fragment)
    }

    override fun onFragmentChangeListener(fragment: Fragment?, boolean: Boolean) {
        onFragmentChange1(fragment, boolean)
    }

    override fun onFragmentChangeListener(popFragment: String, fragment: Fragment?) {
        onFragmentChangeWithPop(popFragment, fragment)
    }

    override fun onBackButtonPressed() {
        onBackPressed()
    }

    override fun onProgressVisibility(isVisible: Boolean) {
        progressDialogueVisibility(isVisible)
    }

    override fun onBackPressed() {
        Utils.hideKeyboard(this, this.currentFocus)
        if (supportFragmentManager.backStackEntryCount > 1) {
            super.onBackPressed()
        } else {
            if (doubleBackToExitCheck) {
                finish()
                return
            }
            doubleBackToExitCheck = true
            Toast.makeText(this, getString(R.string.string_press_again_to_exit), Toast.LENGTH_LONG)
                .show()
            Handler(Looper.getMainLooper()).postDelayed(
                Runnable { doubleBackToExitCheck = false },
                2000
            )
        }
    }
}