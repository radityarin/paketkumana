package com.radityarin.paketkumana.data

import com.radityarin.paketkumana.data.source.local.LocalDataSource
import com.radityarin.paketkumana.data.source.remote.RemoteDataSource
import com.radityarin.paketkumana.data.source.remote.network.ApiResponse
import com.radityarin.paketkumana.data.source.remote.response.cekresi.CekResiResponse
import com.radityarin.paketkumana.data.source.remote.response.listcourier.ListCourierResponse
import com.radityarin.paketkumana.domain.model.Courier
import com.radityarin.paketkumana.domain.repository.AppRepository
import com.radityarin.paketkumana.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : AppRepository {

    override fun getAllListCourier(): Flow<Resource<List<Courier>>> =
        object : NetworkBoundResource<List<Courier>, ListCourierResponse>() {

            override fun loadFromDB(): Flow<List<Courier>> {
                return localDataSource.getListCourier().map {
                    DataMapper.mapListCourierEntityToListCourier(it)
                }
            }

            override fun shouldFetch(data: List<Courier>?): Boolean = data?.size == 0

            override suspend fun createCall(): Flow<ApiResponse<ListCourierResponse>> =
                remoteDataSource.getListCourier()

            override suspend fun saveCallResult(data: ListCourierResponse) {
                val courierList =
                    DataMapper.mapListCourierResponseToListCourier(data)
                localDataSource.insertCouriers(courierList)
            }

        }.asFlow()

    override suspend fun getCekResi(
        courierName: String,
        awb: String
    ): Flow<CekResiResponse> {
        return remoteDataSource.getCekResi(courier = courierName, awb = awb)
    }

}