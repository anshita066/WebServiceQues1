package com.example.webserviceques1

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

//    @GET("posts")
//    fun getId(): retrofit2.Call<List<DataModelItem>>


    @GET("posts")
    fun getAId(@Query("id") id: Int): retrofit2.Call<List<DataModelItem>>
}