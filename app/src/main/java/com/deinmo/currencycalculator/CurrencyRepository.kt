package com.deinmo.currencycalculator

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.MutableLiveData
import com.deinmo.currencycalculator.NetworkServices.CurrencyInterface
import com.deinmo.currencycalculator.NetworkServices.DataService

class CurrencyRepository {
    constructor() {
        currencyInterface = DataService.getclient().create(CurrencyInterface::class.java)

    }
    companion object {
        lateinit var currencyRepository: CurrencyRepository
        fun getInstance(): CurrencyRepository {
            if (currencyRepository == null) {
                currencyRepository = CurrencyRepository()
            }
            return currencyRepository
        }
    }
        private var currencyInterface: CurrencyInterface

        suspend fun conversion(from:String, to:String, amount:Int):MutableLiveData<ConversionClass> {
            var convert:MutableLiveData<ConversionClass> = MutableLiveData()
            val result = currencyInterface.convert(from,to,amount)
            if (result != null)
                convert.setValue(result.body())
            return convert
        }
    suspend fun symbols(): MutableLiveData<CurrencySymbols> {
        var strings:MutableLiveData<CurrencySymbols> = MutableLiveData()
        val result = currencyInterface.getsymbols()
        if (result != null)
            strings.value = result.body()
        return strings
    }
}

