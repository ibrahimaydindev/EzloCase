package com.ibrahimaydindev.ezlocase.model

data class Device(
    val Firmware: String,
    val InternalIP: String,
    val LastAliveReported: String,
    val MacAddress: String,
    val PK_Account: Int,
    val PK_Device: Int,
    val PK_DeviceSubType: Int,
    val PK_DeviceType: Int,
    val Platform: String,
    val Server_Account: String,
    val Server_Device: String,
    val Server_Event: String
)