package com.radityarin.paketkumana.data.source.remote.response.listcourier


import com.google.gson.annotations.SerializedName

data class ListCourierResponseItem(
    @SerializedName("code")
    val code: String,
    @SerializedName("description")
    val description: String
)