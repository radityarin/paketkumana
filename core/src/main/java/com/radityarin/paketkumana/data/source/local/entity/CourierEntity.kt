package com.radityarin.paketkumana.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "courier")
data class CourierEntity(
    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "code")
    var code: String,

    @ColumnInfo(name = "description")
    var description: String
)