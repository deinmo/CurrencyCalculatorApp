package com.deinmo.currencycalculator.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deinmo.currencycalculator.data.remote.dtos.conversionclass.ConversionClass
import com.deinmo.currencycalculator.data.remote.dtos.currencysymbols.CurrencySymbols
import com.deinmo.currencycalculator.domain.CurrencyRepositoryImpl
import kotlinx.coroutines.launch
import java.lang.NumberFormatException

class CurrencyViewModel : ViewModel() {
    lateinit var mutableLiveData: MutableLiveData<ConversionClass>
    lateinit var stringmutable: MutableLiveData<CurrencySymbols>
    lateinit var currencyRepository: CurrencyRepositoryImpl
    init {
        viewModelScope.launch {
            currencyRepository = CurrencyRepositoryImpl.getInstance()
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