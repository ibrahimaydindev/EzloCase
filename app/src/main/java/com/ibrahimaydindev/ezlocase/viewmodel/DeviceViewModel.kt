package com.ibrahimaydindev.ezlocase.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahimaydindev.ezlocase.model.Deviceresponse
import com.ibrahimaydindev.ezlocase.repository.DeviceRepository
import com.ibrahimaydindev.ezlocase.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class DeviceViewModel(
    private val repository: DeviceRepository
) : ViewModel() {

    val getDevices: MutableLiveData<Resource<Deviceresponse>> = MutableLiveData()
    var getDevicesPage = 1
    var getDevicesResponse: Deviceresponse? = null

    init {
       getDevices()
    }

    private fun handleDevicesResponse(response: Response<Deviceresponse>): Resource<Deviceresponse> {
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
    fun getDevices() = viewModelScope.launch {
        getDevices.postValue(Resource.Loading())
        val response = repository.getRealDevices()
        getDevices.postValue(handleDevicesResponse(response))
    }
}
