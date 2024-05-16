package com.evaluation.testproject.views.fragments

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.evaluation.testproject.viewModels.MainVM
import com.evaluation.testproject.views.adapters.CategoriesAdapter
import com.evaluation.testproject.views.fragments.base.BaseFragment
import com.evaluation.testproject.vmCallbacks.category.MainCallBack
import com.goally.goallyapp.R
import com.goally.goallyapp.databinding.FragmentHomeBinding


class HomeFragment : BaseFragment() {
    private val TAG = HomeFragment::class.java.simpleName
    private lateinit var binding: FragmentHomeBinding

    var confirmationDialog: AlertDialog? = null

    private val homeVM: MainVM by lazy {
        ViewModelProvider(this).get(
            MainVM::class.java
        )
    }

    private val categoriesAdapter: CategoriesAdapter by lazy {
        CategoriesAdapter(homeVM)
    }

    override fun onResume() {
        super.onResume()

        if (homeVM.topResponse.value == null){
            homeVM.getAPi()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_home, container, false)
            binding = FragmentHomeBinding.bind(mView!!)
                .apply {
                    this.lifecycleOwner = this@HomeFragment
                    this.viewModel = homeVM
                }
        }
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeVM.getFragmentCB().observe(viewLifecycleOwner, vmCallbackObserver)
        homeVM.getCallback().observe(viewLifecycleOwner, callbackObserver)

        init()
    }

    private val callbackObserver = Observer<MainCallBack> { callBack ->
        when (callBack) {
            MainCallBack.PopulateData -> {
                populateAdapter()
            }

            else -> {

            }
        }
    }

    private fun init() {
        binding.rvImages.apply {
            adapter = categoriesAdapter
        }
    }

    private fun populateAdapter() {
        binding.rvImages.adapter?.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        homeVM.clearObservers(vmCallbackObserver)
        homeVM.remove()
        super.onDestroyView()
    }
}