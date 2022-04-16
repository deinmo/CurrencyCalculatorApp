package com.deinmo.currencycalculator.NetworkServices

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataService {
    class TokenInteceptor():Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val url = chain.request().url().newBuilder().addQueryParameter("accesskey","0749899fce447268bb93624ac8a873fa").build()
            val original = chain.request().newBuilder().url(url).build()
            return chain.proceed(original)
        }
    }

    companion object{
        fun getclient():Retrofit{
            var interceptor = HttpLoggingInterceptor()
            val level = interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            var tokenInteceptor = TokenInteceptor()
            var client = OkHttpClient.Builder().addInterceptor(tokenInteceptor).addInterceptor(interceptor).build()
            var retrofit:Retrofit = Retrofit.Builder().baseUrl("https://data.fixer.io/api/")
                .addConverterFactory(GsonConverterFactory.create()).client(client).build()
            return retrofit
        }
    }
}