package com.ibrahimaydindev.ezlocase.adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ibrahimaydindev.ezlocase.R
import com.ibrahimaydindev.ezlocase.adapter.DeviceAdapter.DeviceViewHolder
import com.ibrahimaydindev.ezlocase.database.DeviceDatabase
import com.ibrahimaydindev.ezlocase.model.Device
import com.ibrahimaydindev.ezlocase.repository.DeviceRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

            when (devices.Platform) {
                "Sercomm G450" -> {
                    findViewById<ImageView>(R.id.deviceImage).setImageResource(R.drawable.vera_plus_big)
                }

                "Sercomm G550" -> {
                    findViewById<ImageView>(R.id.deviceImage).setImageResource(R.drawable.vera_secure_big)
                }

                "MiCasaVerde VeraLite" -> {
                    findViewById<ImageView>(R.id.deviceImage).setImageResource(R.drawable.vera_edge_big)
                }

                "Sercomm NA900" -> {
                    findViewById<ImageView>(R.id.deviceImage).setImageResource(R.drawable.vera_edge_big)
                }

                "Sercomm NA301" -> {
                    findViewById<ImageView>(R.id.deviceImage).setImageResource(R.drawable.vera_edge_big)
                }

                "Sercomm NA930" -> {
                    findViewById<ImageView>(R.id.deviceImage).setImageResource(R.drawable.vera_edge_big)
                }

                else -> {
                    findViewById<ImageView>(R.id.deviceImage).setImageResource(R.drawable.vera_edge_big)
                }
            }
            findViewById<TextView>(R.id.deviceHomeNumber).text = devices.Platform
            findViewById<TextView>(R.id.deviceSnNumber).text = devices.PK_Device.toString()
            setOnClickListener {
                onItemClickListener?.let {
                    it(devices)
                }
            }
            setOnLongClickListener {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Delete Device")
                builder.setMessage("Are you sure you want to delete this device?")
                builder.setPositiveButton("Yes") { dialog, which ->
                    onItemLongClickListener?.let {
                        val repository = DeviceRepository(DeviceDatabase(context))
                        CoroutineScope(Dispatchers.Default).launch {
                            repository.deleteDevice(devices)
                        }
                    }
                }
                builder.setNegativeButton("No") { dialog, which ->
                    dialog.dismiss()
                }
                builder.show()
                true
            }
        }
    }

    fun setOnItemClickListener(listen: (Device) -> Unit) {
        onItemClickListener = listen
    }
    private var onItemClickListener: ((Device) -> Unit)? = null
    fun setOnItemLongClickListener(listen: (Device) -> Unit) {
        onItemLongClickListener = listen
    }
    private var onItemLongClickListener: ((Device) -> Unit)? = null
}