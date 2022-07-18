package com.deinmo.currencycalculator.domain

import androidx.lifecycle.MutableLiveData
import com.deinmo.currencycalculator.data.remote.dtos.conversionclass.ConversionClass
import com.deinmo.currencycalculator.data.remote.dtos.currencysymbols.CurrencySymbols
import com.deinmo.currencycalculator.data.remote.CurrencyInterface
import com.deinmo.currencycalculator.NetworkServices.DataService
import com.deinmo.currencycalculator.data.CurrencyRepository
import okhttp3.ResponseBody
import retrofit2.Response

class CurrencyRepositoryImpl @Inject constructor(
    
):CurrencyRepository {

    override suspend fun getsymbols() {
        TODO("Not yet implemented")
    }

    override suspend fun getrates() {
        TODO("Not yet implemented")
    }

    override suspend fun images() {
        TODO("Not yet implemented")
    }


}

