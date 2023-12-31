package com.ibrahimaydindev.ezlocase.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ibrahimaydindev.ezlocase.model.Device

@Dao
interface DeviceDao {
    @Query("SELECT * FROM devices")
    fun getAllDevices(): LiveData<List<Device>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDevice(device: Device)

    @Delete
    suspend fun deleteDevice(device: Device)

    @Query("DELETE  FROM devices")
    suspend fun deleteAllDevices()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllDevices (devices: MutableList<Device>)
}