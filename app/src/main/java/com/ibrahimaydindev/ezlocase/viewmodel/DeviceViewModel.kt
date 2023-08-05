package com.ibrahimaydindev.ezlocase.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahimaydindev.ezlocase.model.Device
import com.ibrahimaydindev.ezlocase.repository.DeviceRepository
import kotlinx.coroutines.launch

class DeviceViewModel(
    private val repository: DeviceRepository
) : ViewModel() {

    init {
        getAllDevices()
    }

    fun insertDevice(device: Device) = viewModelScope.launch {
        repository.insertDevice(device)
    }

    fun deleteDevice(device: Device) = viewModelScope.launch {
        repository.deleteDevice(device)
    }

    fun getAllDevices() = repository.getAllDevices()
}