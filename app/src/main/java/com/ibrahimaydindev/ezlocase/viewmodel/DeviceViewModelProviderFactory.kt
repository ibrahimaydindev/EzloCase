package com.ibrahimaydindev.ezlocase.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ibrahimaydindev.ezlocase.repository.DeviceRepository

class DeviceViewModelProviderFactory(private val repository: DeviceRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DeviceViewModel(repository) as T
    }
}