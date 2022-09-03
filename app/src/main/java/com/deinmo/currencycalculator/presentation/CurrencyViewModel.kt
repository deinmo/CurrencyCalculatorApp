package com.deinmo.currencycalculator.presentation

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deinmo.currencycalculator.data.CurrencyRepository
import com.deinmo.currencycalculator.data.local.CurrencyDao
import com.deinmo.currencycalculator.data.local.CurrencyEntity
import com.deinmo.currencycalculator.data.remote.dtos.conversionclass.ConversionClass
import com.deinmo.currencycalculator.data.remote.dtos.currencysymbols.CurrencySymbols
import com.deinmo.currencycalculator.domain.CurrencyRepositoryImpl
import com.deinmo.currencycalculator.domain.models.CountryModel
import com.deinmo.currencycalculator.utils.ConnectivityObserver
import com.deinmo.currencycalculator.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import java.lang.NumberFormatException
import java.security.AccessController.getContext
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.N)
@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val repository: CurrencyRepository,
    observer: ConnectivityObserver
) : ViewModel() {

    var state by mutableStateOf(CurrenciesListState())

    init {
           /* observer.observe().onEach {
                if (it == ConnectivityObserver.Status.Available)
                    getRates(true)
                else
                    getRates(false)
            }*/
        getRates(true)
    }

    private fun getRates(fetchFromRemote: Boolean){
        viewModelScope.launch {
            repository.getRates(fetchFromRemote)
                .collect{result ->
                    when(result){
                        is Resource.Loading -> {
                            state = state.copy(isLoading =  true)
                        }
                        is Resource.Success -> {
                            state = state.copy(counrtyModels = result.data ?: emptyList())
                        }
                        is Resource.Error -> {

                        }
                    }
                }
        }
    }
}