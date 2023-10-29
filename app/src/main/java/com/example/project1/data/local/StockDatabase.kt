package com.example.project1.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Stock::class],
    version = 1
)
abstract class StockDatabase: RoomDatabase() {
    abstract fun stockDao(): StockDao

    companion object {

        @Volatile
        private var INSTANCE: StockDatabase? = null
        fun builtDatabase(context: Context): StockDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StockDatabase::class.java,
                    "stock_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
//скоупы обратить внимание
//dependency inj
//класс application
//сделать что-то на xml
//сервис на котлине
// вынести обращение в репозиторий - изолировать вьюмодел