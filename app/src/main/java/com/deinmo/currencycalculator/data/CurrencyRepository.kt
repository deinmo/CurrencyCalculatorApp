package com.deinmo.currencycalculator.data

import com.deinmo.currencycalculator.data.remote.dtos.currencysymbols.CurrencySymbols
import com.deinmo.currencycalculator.data.remote.dtos.latestrates.Latest
import com.deinmo.currencycalculator.domain.models.CountryModel
import com.deinmo.currencycalculator.utils.Resource
import kotlinx.coroutines.flow.Flow


interface CurrencyRepository {

    suspend fun getRates(fetchFromRemote: Boolean): Flow<Resource<List<CountryModel>>>

    suspend fun getLatestRates():Flow<Resource<Latest>>?

    suspend fun getSymbols():Flow<Resource<CurrencySymbols>>

    suspend fun images()

    suspend fun saveCurrency(countryModel: CountryModel)
}