package com.ibrahimaydindev.ezlocase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ibrahimaydindev.ezlocase.R
import com.ibrahimaydindev.ezlocase.adapter.DeviceAdapter.DeviceViewHolder
import com.ibrahimaydindev.ezlocase.model.Device

class DeviceAdapter : RecyclerView.Adapter<DeviceViewHolder>() {

    inner class DeviceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Device>() {
        override fun areItemsTheSame(oldItem: Device, newItem: Device): Boolean {
            return oldItem.PK_Device == newItem.PK_Device
        }

        override fun areContentsTheSame(oldItem: Device, newItem: Device): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        return DeviceViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_device,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        val devices = differ.currentList[position]
        holder.itemView.apply {

            findViewById<TextView>(R.id.deviceHomeNumber).text = devices.Platform
            findViewById<TextView>(R.id.deviceSnNumber).text = devices.MacAddress
            setOnClickListener {
                onItemClickListener?.let {
                    it(devices)
                }
            }
        }
    }


    fun setOnItemClickListener(listen: (Device) -> Unit) {
        onItemClickListener = listen
    }

    private var onItemClickListener: ((Device) -> Unit)? = null
}