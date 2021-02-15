package com.radityarin.paketkumana.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.radityarin.paketkumana.data.source.local.entity.CourierEntity

@Database(entities = [CourierEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appDao(): AppDao
}