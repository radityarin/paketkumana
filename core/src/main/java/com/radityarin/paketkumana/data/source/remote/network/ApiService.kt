package com.radityarin.paketkumana.data.source.remote.network

import com.radityarin.paketkumana.data.source.remote.response.cekresi.CekResiResponse
import com.radityarin.paketkumana.data.source.remote.response.listcourier.ListCourierResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("list_courier")
    suspend fun getListCourier(
        @Query("api_key") apiKey: String = Keys.apiKey()
    ): ListCourierResponse

    @GET("track")
    suspend fun getCekResi(
        @Query("api_key") apiKey: String = Keys.apiKey(),
        @Query("courier") courier: String,
        @Query("awb") awb: String
    ): CekResiResponse
}

object Keys {
    fun apiKey() = "ee97c8617c894f168e930d8985f57bf9757c09b26c5effd09e54c60c9cc6a95f"
}