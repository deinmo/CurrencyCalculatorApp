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
        var currencyRepository = CurrencyRepository()
        fun getInstance(): CurrencyRepository {
            return currencyRepository
        }
    }
        private var currencyInterface: CurrencyInterface

        suspend fun conversion(from:String, to:String, amount:Int):MutableLiveData<ConversionClass> {
            var convert:MutableLiveData<ConversionClass> = MutableLiveData()
            val result = currencyInterface.convert(from,to,amount)
            convert.setValue(result.body())
            return convert
        }
    suspend fun symbols(): MutableLiveData<CurrencySymbols> {
        var strings:MutableLiveData<CurrencySymbols> = MutableLiveData()
        val result = currencyInterface.getsymbols()
        strings.value = result.body()
        return strings
    }
}

