package com.deinmo.currencycalculator.presentation.conversion

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deinmo.currencycalculator.data.CurrencyRepository
import com.deinmo.currencycalculator.utils.ConnectivityObserver
import com.deinmo.currencycalculator.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.N)
@HiltViewModel
class ConversionViewModel @Inject constructor(
    private val repository: CurrencyRepository,
    observer: ConnectivityObserver
): ViewModel() {
    var state by mutableStateOf(ConversionState())

    init {
          /*  observer.observe().onEach {
                if (it == ConnectivityObserver.Status.Available) {
                    getRates(true)
                    Log.d("status","Availabe")
                }
                else {
                    getRates(false)
                    Log.d("status","NOT Availabe")
                }
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
                            state = state.copy(counrtyModels = result.data!!)
                        }
                        is Resource.Error -> {

                        }
                    }
                }
        }
    }

    fun convert(from: String, to: String, amount: Double): Double {
        var toAmountRate = 0.0
        state.counrtyModels.onEach {
            if (it.symbol == to)
                toAmountRate = it.rate!!
        }
        var fromAmountRate = 0.0
        state.counrtyModels.onEach {
            if(it.symbol == from)
                fromAmountRate = it.rate!!
        }

        val result1 = amount * toAmountRate
        val result = result1/fromAmountRate
        return result
    }
}