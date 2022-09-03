package com.deinmo.currencycalculator.di

import android.app.Application
import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.room.Room
import com.deinmo.currencycalculator.data.CurrencyRepository
import com.deinmo.currencycalculator.data.local.CurrencyDao
import com.deinmo.currencycalculator.data.local.CurrencyDatabase
import com.deinmo.currencycalculator.data.remote.CurrencyInterface
import com.deinmo.currencycalculator.domain.CurrencyRepositoryImpl
import com.deinmo.currencycalculator.utils.ConnectivityObserver
import com.deinmo.currencycalculator.utils.NetworkConnectivityObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    class TokenInterceptor: Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            var url: Request = chain.request()
            url =  url.newBuilder().addHeader("access_key","0749899fce447268bb93624ac8a873fa").build()
            return chain.proceed(url)
        }
    }

    @Provides
    @Singleton
    fun provideapiservice(): CurrencyInterface {
        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        var client = OkHttpClient.Builder().addInterceptor(TokenInterceptor()).addInterceptor(interceptor).build()
        return Retrofit.Builder()
            .baseUrl("http://data.fixer.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(CurrencyInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application): CurrencyDao {
        return Room.databaseBuilder(
            app,
            CurrencyDatabase::class.java,
            "Currency.db"
        ).build().dao
    }

    @Provides
    @Singleton
    fun provideRepository(api: CurrencyInterface, dao: CurrencyDao): CurrencyRepository {
        return CurrencyRepositoryImpl(api, dao)
    }

    @Provides
    @Singleton
    fun provideConnectivityCheck(@ApplicationContext applicationContext: Context): ConnectivityObserver {
        return NetworkConnectivityObserver(applicationContext)
    }
}