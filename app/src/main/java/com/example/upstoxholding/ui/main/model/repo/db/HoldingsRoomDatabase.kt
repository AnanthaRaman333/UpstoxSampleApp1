package com.example.upstoxholding.ui.main.model.repo.db

import android.content.Context
import androidx.room.*

@Database(entities = [HoldingsEntity::class], version = 1, exportSchema = false)
public abstract class HoldingsRoomDatabase : RoomDatabase() {

    abstract fun holdingsDao(): HoldingsDAO

    companion object {
        @Volatile
        private var INSTANCE: HoldingsRoomDatabase? = null

        fun getDatabase(context: Context): HoldingsRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HoldingsRoomDatabase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}