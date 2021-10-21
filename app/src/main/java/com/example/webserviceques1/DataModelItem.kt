package com.example.webserviceques1

import com.google.gson.annotations.SerializedName

data class DataModelItem(
    @SerializedName("userId")
    val userId : Int,
    @SerializedName("id")
    val id : Int,
    @SerializedName("title")
    val title : String,
    @SerializedName("body")
    val body : String)