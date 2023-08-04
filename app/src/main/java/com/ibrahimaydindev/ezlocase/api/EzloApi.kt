package com.ibrahimaydindev.ezlocase.api

import com.ibrahimaydindev.ezlocase.model.Iot
import retrofit2.Response


interface EzloApi {
    suspend fun getIoties() : Response<List<Iot>>
}