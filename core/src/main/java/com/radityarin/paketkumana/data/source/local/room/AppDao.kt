package com.radityarin.paketkumana.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.radityarin.paketkumana.data.source.local.entity.CourierEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    @Query("SELECT * FROM courier")
    fun getListCourier(): Flow<List<CourierEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourier(movies: List<CourierEntity>)

}