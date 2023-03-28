package com.example.BolettiShop.db

import android.content.Context
import androidx.room.Database
import com.example.BolettiShop.tables.Product
import androidx.room.RoomDatabase
import kotlin.jvm.Volatile
import androidx.room.Room
import com.example.BolettiShop.tables.Categories
import java.util.concurrent.Executors

@Database(entities = [Product::class, Categories::class], version = 2, exportSchema = false)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun menuDao(): ProductDao?

    companion object {
        @Volatile
        private var INSTANCE: ProductDatabase? = null
        private const val NUMBER_OF_THREADS = 4
        @JvmField
        val dbWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS)
        @JvmStatic
        fun getDatabase(context: Context): ProductDatabase? {
            if (INSTANCE == null) {
                synchronized(ProductDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            ProductDatabase::class.java, "database"
                        )
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}