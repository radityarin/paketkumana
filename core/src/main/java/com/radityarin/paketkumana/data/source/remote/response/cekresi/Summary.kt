package com.radityarin.paketkumana.data.source.remote.response.cekresi


import com.google.gson.annotations.SerializedName

data class Summary(
    @SerializedName("amount")
    val amount: String,
    @SerializedName("awb")
    val awb: String,
    @SerializedName("courier")
    val courier: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("desc")
    val desc: String,
    @SerializedName("service")
    val service: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("weight")
    val weight: String
)