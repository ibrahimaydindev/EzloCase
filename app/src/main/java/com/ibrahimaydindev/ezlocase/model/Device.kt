package com.ibrahimaydindev.ezlocase.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity(
    tableName = "devices"
)
data class Device(
    @SerializedName("Firmware")
    val Firmware: String,
    @SerializedName("InternalIP")
    val InternalIP: String,
    @SerializedName("LastAliveReported")
    val LastAliveReported: String,
    @SerializedName("MacAddress")
    val MacAddress: String,
    @SerializedName("PK_Account")
    val PK_Account: Int,
    @SerializedName("PK_Device")
    val PK_Device: Int,
    @SerializedName("PK_DeviceSubType")
    val PK_DeviceSubType: Int,
    @SerializedName("PK_DeviceType")
    val PK_DeviceType: Int,
    @SerializedName("Platform")
    val Platform: String,
    @SerializedName("Server_Account")
    val Server_Account: String,
    @SerializedName("Server_Device")
    val Server_Device: String,
    @SerializedName("Server_Event")
    val Server_Event: String
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}