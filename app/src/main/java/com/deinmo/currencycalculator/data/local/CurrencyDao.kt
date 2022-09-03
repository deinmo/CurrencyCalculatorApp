package com.deinmo.currencycalculator.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.deinmo.currencycalculator.data.remote.dtos.currencysymbols.CurrencySymbols
import com.deinmo.currencycalculator.domain.models.CountryModel

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCountry(country: CurrencyEntity)

   /* @Insert
    suspend fun saveCountries(countries: MutableList<CountryModel>)

    @Insert
    suspend fun saveCurrencies(currencySymbols: CurrencySymbols?)*/

    @Query("SELECT * FROM CurrencyEntity WHERE :id = id")
    suspend fun getCountry(id: Int?): CurrencyEntity

    @Query("SELECT * FROM CurrencyEntity")
    suspend fun getCountries(): List<CurrencyEntity>
}