package com.deinmo.currencycalculator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.NumberFormatException
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
    suspend fun getconversion(from: String,to: String,amount: String): MutableLiveData<ConversionClass> {
        var num = 0
        try {
             num = amount.toInt()
        }catch (e: NumberFormatException){
            num = 0
        }

        mutableLiveData = currencyRepository.conversion(from,to,num)
        return mutableLiveData
    }
    suspend fun getsymbols(): MutableLiveData<CurrencySymbols> {
        stringmutable = currencyRepository.symbols()
        return stringmutable
    }
}