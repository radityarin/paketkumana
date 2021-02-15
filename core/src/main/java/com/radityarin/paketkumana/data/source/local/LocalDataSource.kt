package com.radityarin.paketkumana.data.source.local

import com.radityarin.paketkumana.data.source.local.entity.CourierEntity
import com.radityarin.paketkumana.data.source.local.room.AppDao
import kotlinx.coroutines.flow.Flow


class LocalDataSource(private val appDao: AppDao) {

    fun getListCourier(): Flow<List<CourierEntity>> = appDao.getListCourier()

    suspend fun insertCouriers(couriers: List<CourierEntity>) = appDao.insertCourier(couriers)

}