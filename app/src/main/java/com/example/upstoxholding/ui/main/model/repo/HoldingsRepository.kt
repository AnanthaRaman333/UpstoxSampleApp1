package com.example.upstoxholding.ui.main.model.repo

import com.example.upstoxholding.ui.main.model.repo.db.HoldingsDAO
import com.example.upstoxholding.ui.main.model.repo.db.HoldingsEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HoldingsRepository @Inject constructor(
    private val apiService: HoldingsAPIInterface,
    private val holdingsDAO: HoldingsDAO
) :
    Repository {

    val allHoldings: Flow<List<HoldingsEntity>> = holdingsDAO.getAllHoldings()

    suspend fun updateHoldings() {
        val baseResponse = apiService.getHoldings()
        if (baseResponse.isSuccessful) {
            holdingsDAO.insertAllHoldings(baseResponse.body()?.holdings ?: emptyList())
        }
    }
}