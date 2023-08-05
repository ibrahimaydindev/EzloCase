package com.ibrahimaydindev.ezlocase.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.ibrahimaydindev.ezlocase.R
import com.ibrahimaydindev.ezlocase.activity.MainActivity
import com.ibrahimaydindev.ezlocase.databinding.FragmentDetailBinding
import com.ibrahimaydindev.ezlocase.model.Device
import com.ibrahimaydindev.ezlocase.viewmodel.DeviceViewModel


class DetailFragment : Fragment(R.layout.fragment_detail) {

    lateinit var deviceViewModel: DeviceViewModel
    private lateinit var deviceBinding: FragmentDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        deviceBinding = FragmentDetailBinding.inflate(inflater, container, false)
        return deviceBinding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDetailBinding.bind(view)
        deviceBinding = binding
        deviceViewModel = (activity as MainActivity).viewModel

        val bundle = arguments

        if (bundle != null) {
            if (bundle.getSerializable("device") != null) {
                val device = bundle.getSerializable("device") as Device
                deviceBinding.apply {
                    when (device.Platform) {
                        "Sercomm G450" -> {
                            binding.deviceDetailImage.setImageResource(R.drawable.vera_plus_big)
                        }
                        "Sercomm G550" -> {
                            binding.deviceDetailImage.setImageResource(R.drawable.vera_secure_big)
                        }
                        "MiCasaVerde VeraLite" -> {
                            binding.deviceDetailImage.setImageResource(R.drawable.vera_edge_big)
                        }
                        "Sercomm NA900" -> {
                            binding.deviceDetailImage.setImageResource(R.drawable.vera_edge_big)
                        }
                        "Sercomm NA301" -> {
                            binding.deviceDetailImage.setImageResource(R.drawable.vera_edge_big)
                        }
                        "Sercomm NA930" -> {
                            binding.deviceDetailImage.setImageResource(R.drawable.vera_edge_big)
                        }
                        else -> {
                            binding.deviceDetailImage.setImageResource(R.drawable.vera_edge_big)
                        }
                    }
                    binding.deviceMacDetail.setText("MAC Adress:"+device.MacAddress)
                    binding.deviceSnDetail.text = device.Server_Event
                    binding.deviceDetailHomeNumber.text = device.PK_Device.toString()
                    binding.deviceFirmwareDetail.text = device.Firmware
                    binding.newsDescrssiption.text = device.InternalIP

                }
            }else{
                deviceBinding.apply {
                    binding.deviceMacDetail.text = "null"
                    binding.deviceSnDetail.text = "null"
                    binding.deviceDetailHomeNumber.text = "null"
                }
            }

        }else{
            deviceBinding.apply {
                binding.deviceMacDetail.text = "null"
                binding.deviceSnDetail.text = "null"
                binding.deviceDetailHomeNumber.text = "null"
            }
        }
    }

}
