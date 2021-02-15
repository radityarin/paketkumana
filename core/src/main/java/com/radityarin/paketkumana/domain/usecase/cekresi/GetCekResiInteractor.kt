package com.radityarin.paketkumana.domain.usecase.cekresi

import com.radityarin.paketkumana.data.source.remote.response.cekresi.CekResiResponse
import com.radityarin.paketkumana.domain.repository.IAppRepository
import kotlinx.coroutines.flow.Flow

class GetCekResiInteractor(private val repository: IAppRepository) :
    GetCekResiUseCase {
    override suspend fun getCekResi(courierName: String, awb: String): Flow<CekResiResponse> =
        repository.getCekResi(courierName = courierName, awb = awb)
}