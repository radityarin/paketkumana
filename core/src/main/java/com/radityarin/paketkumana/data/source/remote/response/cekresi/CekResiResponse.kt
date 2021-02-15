package com.radityarin.paketkumana.data.source.remote.response.cekresi


import com.google.gson.annotations.SerializedName

data class CekResiResponse(
    @SerializedName("data")
    val cekResi: CekResi? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("status")
    val status: Int? = null
)