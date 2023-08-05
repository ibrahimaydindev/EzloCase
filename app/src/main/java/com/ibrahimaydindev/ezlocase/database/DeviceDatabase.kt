package com.ibrahimaydindev.ezlocase.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ibrahimaydindev.ezlocase.model.Device


@Database(
    entities = [Device::class],
    version = 1
)
abstract class DeviceDatabase : RoomDatabase() {

    abstract fun getDeviceDao(): DeviceDao
    companion object {
        @Volatile
        private var instance: DeviceDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }
        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                DeviceDatabase::class.java,
                "device_db.db"
            ).build()
    }
}