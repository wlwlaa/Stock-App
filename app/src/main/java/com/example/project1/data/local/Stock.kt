package com.example.project1.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "stocks")
data class Stock(
    @PrimaryKey
    @ColumnInfo(name = "symbol")
    val symbol : String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "type")
    val type: String
)
