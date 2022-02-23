package com.example.upstoxholding.ui.main.model.repo.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "holdings_table")
data class HoldingsEntity(
    @SerializedName("symbol") @PrimaryKey @ColumnInfo(name = "symbol") val symbol: String,
    @SerializedName("quantity") @ColumnInfo(name = "quantity") val quantity: Int,
    @SerializedName("avg_price") @ColumnInfo(name = "avg_price") val avgPrice: Double,
    @SerializedName("ltp") @ColumnInfo(name = "ltp") val ltp: Double,
    @SerializedName("close") @ColumnInfo(name = "close") val close: Double
)
