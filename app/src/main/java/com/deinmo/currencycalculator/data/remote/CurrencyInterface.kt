package com.deinmo.currencycalculator.data.remote

import com.deinmo.currencycalculator.data.remote.dtos.conversionclass.ConversionClass
import com.deinmo.currencycalculator.data.remote.dtos.currencysymbols.CurrencySymbols
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyInterface {
    @GET("convert")
    suspend fun convert(@Query("")from:String,@Query("")to:String,@Query("")amount:Int):Response<ConversionClass>

    @GET("symbols")
    suspend fun getsymbols():Response<CurrencySymbols>

    @GET("latest")
    suspend fun getlatestrates():ResponseBody
}