package com.deinmo.currencycalculator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.time.temporal.TemporalAmount

class CurrencyViewModel : ViewModel() {
    lateinit var mutableLiveData: MutableLiveData<ConversionClass>
    lateinit var stringmutable: MutableLiveData<CurrencySymbols>
    lateinit var currencyRepository: CurrencyRepository
    init {
        viewModelScope.launch {
            currencyRepository = CurrencyRepository.getInstance()
        }

    }
    suspend fun getconversion(amount: Int): MutableLiveData<ConversionClass> {
        mutableLiveData = currencyRepository.conversion("AED","ALL",amount)
        return mutableLiveData
    }
    suspend fun getsymbols(): MutableLiveData<CurrencySymbols> {
        stringmutable = currencyRepository.symbols()
        return stringmutable
    }
}