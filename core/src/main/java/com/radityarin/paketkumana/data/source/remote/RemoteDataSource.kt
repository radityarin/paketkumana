package com.radityarin.paketkumana.data.source.remote

import android.content.Context
import com.radityarin.paketkumana.data.source.remote.network.ApiResponse
import com.radityarin.paketkumana.data.source.remote.network.ApiService
import com.radityarin.paketkumana.data.source.remote.response.cekresi.CekResiResponse
import com.radityarin.paketkumana.data.source.remote.response.listcourier.ListCourierResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

class RemoteDataSource(private val apiService: ApiService, private val context: Context) {

    suspend fun getListCourier(): Flow<ApiResponse<ListCourierResponse>> {
        return flow {
            try {
                val response = apiService.getListCourier()
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                Timber.e(e.message.toString())
                emit(ApiResponse.Error("List Courier not found"))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getCekResi(courier: String, awb: String): Flow<CekResiResponse> {
        return flow {
            try {
                val response = apiService.getCekResi(courier = courier, awb = awb)
                if (response.status == 200) {
                    emit(response)
                } else {
                    val emptyResponse = CekResiResponse(null, null,null)
                    emit(emptyResponse)
                }
            } catch (e: Exception) {
                val emptyResponse = CekResiResponse(null, "Data not Found",null)
                Timber.e(e.message.toString())
                emit(emptyResponse)
            }
        }.flowOn(Dispatchers.IO)
    }

}