package com.deinmo.currencycalculator.NetworkServices

import com.deinmo.currencycalculator.ConversionClass
import com.deinmo.currencycalculator.CurrencySymbols
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyInterface {
    @GET("convert")
    suspend fun convert(@Query("")from:String,@Query("")to:String,@Query("")amount:Int):Response<ConversionClass>

    @GET("symbols")
    suspend fun getsymbols():Response<CurrencySymbols>
}