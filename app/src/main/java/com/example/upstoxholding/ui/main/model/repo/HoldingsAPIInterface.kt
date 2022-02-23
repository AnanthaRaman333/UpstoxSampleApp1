package com.example.upstoxholding.ui.main.model.repo

import com.example.upstoxholding.ui.main.model.HoldingsBaseModel
import retrofit2.Response
import retrofit2.http.GET

interface HoldingsAPIInterface {

    @GET("v3/6d0ad460-f600-47a7-b973-4a779ebbaeaf")
    suspend fun getHoldings(): Response<HoldingsBaseModel>

    companion object {

        const val BASE_URL = "https://run.mocky.io/"
    }
}