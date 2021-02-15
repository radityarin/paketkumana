package com.radityarin.paketkumana.data.source.remote.response.cekresi


import com.google.gson.annotations.SerializedName

data class Detail(
    @SerializedName("destination")
    val destination: String,
    @SerializedName("origin")
    val origin: String,
    @SerializedName("receiver")
    val `receiver`: String,
    @SerializedName("shipper")
    val shipper: String
)