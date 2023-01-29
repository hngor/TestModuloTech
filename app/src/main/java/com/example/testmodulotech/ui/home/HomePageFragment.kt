package com.example.testmodulotech.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testmodulotech.R
import com.example.testmodulotech.databinding.FragmentHomePageBinding
import com.example.testmodulotech.ui.home.adapter.HomePageAdapter
import com.example.testmodulotech.ui.home.model.HomePageUiModel
import com.example.testmodulotech.util.Constants
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomePageFragment : Fragment() {

    private lateinit var homePageAdapter: HomePageAdapter
    private lateinit var viewManager: LinearLayoutManager
    private val viewModel: HomePageViewModel by viewModel()

    private lateinit var binding: FragmentHomePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.data.observe(this) { state -> updateUI(state) }
        viewModel.getHomeData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Init click listeners
        binding.btnRetry.setOnClickListener { viewModel.getHomeData() }

        //Init filter view
        val filtersAdapter = ArrayAdapter(
            requireContext(),
            R.layout.layout_filter_item,
            Constants.PRODUCT_TYPE_FILTER
        )
        binding.autoCompleteTextView.setAdapter(filtersAdapter)
        binding.autoCompleteTextView.setText(Constants.PRODUCT_TYPE_FILTER[0], false)
        binding.autoCompleteTextView.setOnItemClickListener { _, _, _, _ ->
            viewModel.getFilteredDeviceList(binding.autoCompleteTextView.text.toString())
        }

        //Init recyclerview
        viewManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        val dividerItemDecoration = DividerItemDecoration(context, viewManager.orientation)

        homePageAdapter = HomePageAdapter(onDeviceClick = { _ -> },
            onDeleteDevice = { deviceId -> viewModel.deleteDevice(deviceId) })

        binding.recyclerDevices.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = homePageAdapter
        }
        binding.recyclerDevices.addItemDecoration(dividerItemDecoration)
    }

    private fun updateUI(state: HomePageUiModel) {
        when (state) {
            is HomePageUiModel.HomePageData -> showData(state)
            HomePageUiModel.HomePageError -> showError()
            HomePageUiModel.Loading -> {
                hideError()
                showProgressBar()
            }
        }
    }

    private fun showData(data: HomePageUiModel.HomePageData) {
        hideProgressBar()
        hideError()
        homePageAdapter.deviceList = data.deviceList
        homePageAdapter.notifyDataSetChanged()
    }

    private fun showError() {
        binding.layoutError.visibility = View.VISIBLE
    }

    private fun hideError() {
        binding.layoutError.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.layoutProgressBarHome.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.layoutProgressBarHome.visibility = View.GONE
    }
}