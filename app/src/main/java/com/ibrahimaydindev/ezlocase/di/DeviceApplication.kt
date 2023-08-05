package com.ibrahimaydindev.ezlocase.di

import android.app.Application
import com.ibrahimaydindev.ezlocase.database.DeviceDatabase
import com.ibrahimaydindev.ezlocase.repository.DeviceRepository
import com.ibrahimaydindev.ezlocase.viewmodel.DeviceViewModelProviderFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class DeviceApplication: Application(),KodeinAware {
    override val kodein: Kodein =  Kodein.lazy {
        import(androidXModule(this@DeviceApplication))
        bind() from singleton { DeviceDatabase(instance()) }
        bind() from singleton { DeviceRepository(instance()) }
        bind() from provider {
            DeviceViewModelProviderFactory(instance())
        }
    }
}