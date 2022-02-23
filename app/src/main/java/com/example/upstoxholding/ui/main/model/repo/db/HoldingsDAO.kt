package com.example.upstoxholding.ui.main.model.repo.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HoldingsDAO {

    @Query("SELECT * FROM holdings_table ORDER BY symbol ASC")
    fun getAllHoldings() : Flow<List<HoldingsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllHoldings(data: List<HoldingsEntity>)

    @Insert
    suspend fun insert(data : HoldingsEntity)
}