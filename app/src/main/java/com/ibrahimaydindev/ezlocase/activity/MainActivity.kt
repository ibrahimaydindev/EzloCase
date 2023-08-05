package com.ibrahimaydindev.ezlocase.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.ibrahimaydindev.ezlocase.R
import com.ibrahimaydindev.ezlocase.databinding.ActivityMainBinding
import com.ibrahimaydindev.ezlocase.viewmodel.DeviceViewModel
import com.ibrahimaydindev.ezlocase.viewmodel.DeviceViewModelProviderFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(),KodeinAware {
    override val kodein by kodein()
    private val factory : DeviceViewModelProviderFactory by instance()
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: DeviceViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProviders.of(this@MainActivity, factory)[DeviceViewModel::class.java]
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController
    }
}