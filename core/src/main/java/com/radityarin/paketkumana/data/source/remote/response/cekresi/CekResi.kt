package com.radityarin.paketkumana.data.source.remote.response.cekresi


import com.google.gson.annotations.SerializedName

data class CekResi(
    @SerializedName("detail")
    val detail: Detail,
    @SerializedName("history")
    val history: List<History>,
    @SerializedName("summary")
    val summary: Summary
)