package com.deinmo.currencycalculator.domain

import androidx.lifecycle.viewModelScope
import com.deinmo.currencycalculator.data.remote.CurrencyInterface
import com.deinmo.currencycalculator.data.CurrencyRepository
import com.deinmo.currencycalculator.data.local.CurrencyDao
import com.deinmo.currencycalculator.data.local.CurrencyEntity
import com.deinmo.currencycalculator.data.remote.dtos.currencysymbols.CurrencySymbols
import com.deinmo.currencycalculator.data.remote.dtos.latestrates.Latest
import com.deinmo.currencycalculator.domain.models.CountryModel
import com.deinmo.currencycalculator.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyRepositoryImpl @Inject constructor(
    private val api: CurrencyInterface,
    private val dao: CurrencyDao
):CurrencyRepository {

   override suspend fun getRates(fetchFromRemote: Boolean):Flow<Resource<List<CountryModel>>> = flow {
       emit(Resource.Loading())
       val latest = dao.getCountries().map { it.toCountryModel() }
       emit(Resource.Success(latest))

       val isDbEmpty = latest.isEmpty()
       val loadFromCache = !isDbEmpty && !fetchFromRemote
       if(loadFromCache){
           Resource.Loading(false)
           return@flow
       }
       var symbols: CurrencySymbols
       var latestRates: Latest
       var countryModels = mutableListOf<CountryModel>()
       try {
           symbols = api.getSymbols()
           latestRates = api.getLatestRates()
           countryModels = symbols.toCountryModel()
           latestRates?.rates?.onEach { latest->
               for(i in 0..(countryModels.size-1)){
                   if (countryModels[i].symbol == latest.key && countryModels[i].rate != latest.value){
                       countryModels[i].rate = latest.value
                   }
               }
           }
       }catch (e: IOException){
           e.printStackTrace()
           emit(Resource.Error("An unexpected error occured"))
       }
       catch (e: HttpException){
           e.printStackTrace()
           emit(Resource.Error("Server Error"))
       }

       countryModels.onEach { countryModel ->
           saveCurrency(countryModel = countryModel)
       }
       emit(Resource.Loading(false))
       emit(Resource.Success(countryModels as List<CountryModel>))
   } as Flow<Resource<List<CountryModel>>>

    override suspend fun getLatestRates(): Flow<Resource<Latest>> = flow {
        try {
            emit(Resource.Loading())
            val latestRates = api.getLatestRates()
            emit(Resource.Success(latestRates))
        }catch (e: IOException){
            e.printStackTrace()
            emit(Resource.Error("An unexpected error occured"))
        }
        catch (e: HttpException){
            e.printStackTrace()
            emit(Resource.Error("Server Error"))
        }
    }

    override suspend fun getSymbols(): Flow<Resource<CurrencySymbols>> = flow{
        try {
            val symbols = api.getSymbols()
            emit(Resource.Success(symbols))
        }catch (e: IOException){
            e.printStackTrace()
            emit(Resource.Error(""))
        }
        catch (e: HttpException){
            e.printStackTrace()
            emit(Resource.Error(""))
        }
    }


    override suspend fun images() {
        TODO("Not yet implemented")
    }

    override suspend fun saveCurrency(countryModel: CountryModel){
        val currencyEntity = CurrencyEntity(countryname = countryModel.countryName,
            symbol = countryModel.symbol,rate = countryModel.rate,image = null)
            dao.saveCountry(currencyEntity)
    }
}

