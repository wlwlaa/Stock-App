package com.example.project1.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface StockDao {
    @Query("SELECT * from stocks")
    fun getAll(): Flow<List<Stock>>

    @Query("SELECT * FROM stocks WHERE symbol = :symbol")
    suspend fun getBySymbol(symbol: String): Stock?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addStock(stock: Stock)

    @Delete
    suspend fun deleteStock(stock: Stock)

    @Query("DELETE from stocks")
    suspend fun deleteAllStocks()
}