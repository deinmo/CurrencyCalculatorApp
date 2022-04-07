package com.deinmo.currencycalculator.NetworkServices

import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyInterface {
    @GET("convert")
    suspend fun getdata(@Query("")from:String,@Query("")to:String,@Query("")amount:Int)
}