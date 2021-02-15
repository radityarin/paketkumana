package com.radityarin.paketkumana.domain.usecase.cekresi

import com.radityarin.paketkumana.data.source.remote.response.cekresi.CekResiResponse
import kotlinx.coroutines.flow.Flow

interface GetCekResiUseCase {
    suspend fun getCekResi(
        courierName : String,
        awb : String
    ) : Flow<CekResiResponse>
}