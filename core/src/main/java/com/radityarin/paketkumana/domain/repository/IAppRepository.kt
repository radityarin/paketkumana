package com.radityarin.paketkumana.domain.repository

import com.radityarin.paketkumana.data.Resource
import com.radityarin.paketkumana.data.source.remote.network.ApiResponse
import com.radityarin.paketkumana.data.source.remote.response.cekresi.CekResi
import com.radityarin.paketkumana.data.source.remote.response.cekresi.CekResiResponse
import com.radityarin.paketkumana.domain.model.Courier
import kotlinx.coroutines.flow.Flow

interface IAppRepository {
    fun getAllListCourier(): Flow<Resource<List<Courier>>>

    suspend fun getCekResi(
        courierName : String,
        awb: String
    ): Flow<CekResiResponse>

}