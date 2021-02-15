package com.radityarin.paketkumana.data.source.remote.response.cekresi


import com.google.gson.annotations.SerializedName

data class History(
    @SerializedName("date")
    val date: String,
    @SerializedName("desc")
    val desc: String,
    @SerializedName("location")
    val location: String
)