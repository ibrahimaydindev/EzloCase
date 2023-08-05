package com.ibrahimaydindev.ezlocase.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahimaydindev.ezlocase.model.Device
import com.ibrahimaydindev.ezlocase.model.Iot
import com.ibrahimaydindev.ezlocase.repository.DeviceRepository
import com.ibrahimaydindev.ezlocase.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class DeviceViewModel(
    private val repository: DeviceRepository
) : ViewModel() {

    val getDevices: MutableLiveData<Resource<Iot>> = MutableLiveData()
    var getDevicesPage = 1
    var getDevicesResponse: Iot? = null

    init {
        getDevices()
    }

    fun getDevices() = viewModelScope.launch {
        getDevices.postValue(Resource.Loading())
        val response = repository.getRealDevices()
        getDevices.postValue(handleNewsResponse(response))
    }

    private fun handleNewsResponse(response: Response<Iot>): Resource<Iot> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                getDevicesPage++
                if (getDevicesResponse == null) {
                    getDevicesResponse = resultResponse
                } else {
                    val oldDevices = getDevicesResponse?.Devices
                    val newDevices = resultResponse.Devices
                    oldDevices?.addAll(newDevices)
                }
                return Resource.Success(getDevicesResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun saveDevice(device: Device) = viewModelScope.launch {
        repository.insertDevice(device)
    }

    fun deleteDevice(device: Device) = viewModelScope.launch {
        repository.deleteDevice(device)
    }

}
