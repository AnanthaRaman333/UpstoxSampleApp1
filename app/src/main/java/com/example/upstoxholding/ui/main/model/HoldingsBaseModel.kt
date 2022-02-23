package com.example.upstoxholding.ui.main.model

import com.example.upstoxholding.ui.main.model.repo.db.HoldingsEntity
import com.google.gson.annotations.SerializedName

data class HoldingsBaseModel(
    @SerializedName("client_id") val total: Int,
    @SerializedName("timestamp") val timestamp: Long,
    @SerializedName("response_type") val responseType: String,
    @SerializedName("error") val errMsg: String,
    @SerializedName("data") val holdings: List<HoldingsEntity>
)