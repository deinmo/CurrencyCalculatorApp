package com.deinmo.currencycalculator.NetworkServices

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataService {
    companion object{
        fun getclient():Retrofit{
            var interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            var client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            var retrofit:Retrofit = Retrofit.Builder().baseUrl("https://data.fixer.io/api/")
                .addConverterFactory(GsonConverterFactory.create()).client(client).build()
            return retrofit
        }
    }
}