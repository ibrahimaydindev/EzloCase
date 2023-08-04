package com.ibrahimaydindev.ezlocase.repository

import com.ibrahimaydindev.ezlocase.database.DeviceDatabase
import com.ibrahimaydindev.ezlocase.model.Device

class DeviceRepository(val db : DeviceDatabase) {
    fun getAllDevices() = db.getDeviceDao().getAllDevices()
    suspend fun insertDevice(device: Device) = db.getDeviceDao().insertDevice(device)
    suspend fun deleteDevice(device: Device) = db.getDeviceDao().deleteArticle(device)
}