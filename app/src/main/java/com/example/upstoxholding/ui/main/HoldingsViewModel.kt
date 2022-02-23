package com.example.upstoxholding.ui.main

import androidx.lifecycle.*
import com.example.upstoxholding.ui.main.model.repo.HoldingsRepository
import com.example.upstoxholding.ui.main.model.repo.db.HoldingsEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HoldingsViewModel @Inject constructor(private val repository: HoldingsRepository) :
    ViewModel() {

    private val _allHoldings = MutableLiveData<List<HoldingsEntity>>()
    val allHoldings: MutableLiveData<List<HoldingsEntity>>
        get() = _allHoldings

    private val _currentValue = MutableLiveData<Double>()
    val currentValue: MutableLiveData<Double>
        get() = _currentValue


    private val _totalInvestment = MutableLiveData<Double>()
    val totalInvestment: MutableLiveData<Double>
        get() = _totalInvestment


    private val _totalPl = MutableLiveData<Double>()
    val totalPl: MutableLiveData<Double>
        get() = _totalPl


    private val _dayPl = MutableLiveData<Double>()
    val dayPl: MutableLiveData<Double>
        get() = _dayPl

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateHoldings()
            repository.allHoldings.collect { listOfHoldings ->
                var currentHolding = 0.0
                var investment = 0.0
                var todaypl = 0.0

                listOfHoldings.forEach {
                    currentHolding += it.quantity * it.ltp
                    investment += it.quantity * it.avgPrice
                    todaypl += (it.close - it.ltp) * it.quantity
                }

                _allHoldings.postValue(listOfHoldings)
                _totalPl.postValue(currentHolding - investment)
                _currentValue.postValue(currentHolding)
                _totalInvestment.postValue(investment)
                _dayPl.postValue(todaypl)
            }
        }
    }
}