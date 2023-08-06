package com.ibrahimaydindev.ezlocase.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ibrahimaydindev.ezlocase.api.RetrofitInstance
import com.ibrahimaydindev.ezlocase.database.DeviceDatabase
import com.ibrahimaydindev.ezlocase.model.Device

class DeviceRepository(val db : DeviceDatabase) {
    fun getAllDevices() = db.getDeviceDao().getAllDevices()
    suspend fun insertDevice(device: Device) = db.getDeviceDao().insertDevice(device)
    suspend fun deleteDevice(device: Device) = db.getDeviceDao().deleteDevice(device)
    suspend fun getRealDevices() = RetrofitInstance.api.getIoties()
    suspend fun deleteAllDevices() = db.getDeviceDao().deleteAllDevices()
    suspend fun insertAllDevices (devices: MutableList<Device>) = db.getDeviceDao().insertAllDevices(devices)
    suspend fun getAllDevices (devices: LiveData<MutableList<Device>>) = db.getDeviceDao().getAllDevices()


}