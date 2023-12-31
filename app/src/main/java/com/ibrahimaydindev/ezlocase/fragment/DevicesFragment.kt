package com.ibrahimaydindev.ezlocase.fragment

import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.AbsListView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ibrahimaydindev.ezlocase.R
import com.ibrahimaydindev.ezlocase.activity.MainActivity
import com.ibrahimaydindev.ezlocase.adapter.DeviceAdapter
import com.ibrahimaydindev.ezlocase.databinding.FragmentDevicesBinding
import com.ibrahimaydindev.ezlocase.util.Resource
import com.ibrahimaydindev.ezlocase.viewmodel.DeviceViewModel

class DevicesFragment : Fragment(R.layout.fragment_devices) {
    private lateinit var deviceBinding: FragmentDevicesBinding
    private lateinit var deviceViewModel: DeviceViewModel
    private lateinit var deviceAdapter: DeviceAdapter

    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDevicesBinding.bind(view)
        deviceBinding = binding
        deviceAdapter = DeviceAdapter()
        deviceViewModel = (activity as MainActivity).viewModel
        observeLiveData()
        setupRecyclerView()

        deviceAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("device", it)
            }
            deviceAdapter.setOnItemLongClickListener { it ->
                val device = deviceAdapter.differ.currentList[it.id?.toInt()!!]
                val alertDialog = AlertDialog.Builder(requireContext())
                alertDialog.setTitle("Delete Item")
                alertDialog.setMessage("Are you sure you want to delete this device?")
                alertDialog.setPositiveButton("Yes") { _, _ ->
                    deviceAdapter.differ.currentList.toMutableList().remove(device)
                }
                alertDialog.setNegativeButton("No") { _, _ -> }
                alertDialog.create().show()

            }
            findNavController().navigate(
                R.id.action_devicesFragment_to_detailFragment,
                bundle
            )
        }
    }

    private fun setupRecyclerView() {
        deviceBinding.recyclerView.apply {
            adapter = deviceAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@DevicesFragment.scrollListener)
        }
    }

    private fun hideProgressBar() {
        deviceBinding.progressBar.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun showProgressBar() {
        deviceBinding.progressBar.visibility = View.VISIBLE
        isLoading = true
    }

    private var scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val shouldPaginate =
                isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning && isScrolling
            if (shouldPaginate) {
                deviceViewModel.getDevices()
                isScrolling = false
            }
        }
    }
    private fun observeLiveData() {
        deviceViewModel.getDevices.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { deviceResponse ->
                        deviceAdapter.differ.submitList(deviceResponse.Devices.toList())
                    }
                }

                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "Error : $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }
}

