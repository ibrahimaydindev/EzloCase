package com.ibrahimaydindev.ezlocase.api


import com.ibrahimaydindev.ezlocase.model.Deviceresponse
import retrofit2.Response
import retrofit2.http.GET


interface EzloApi {
    @GET("test_android/items.test")
    suspend fun getIoties() : Response<Deviceresponse>
}