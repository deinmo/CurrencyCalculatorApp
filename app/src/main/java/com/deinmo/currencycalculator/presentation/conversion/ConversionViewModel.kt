package com.deinmo.currencycalculator.presentation.conversion

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deinmo.currencycalculator.data.CurrencyRepository
import com.deinmo.currencycalculator.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConversionViewModel @Inject constructor(
    private val repository: CurrencyRepository
): ViewModel() {
    var state by mutableStateOf(ConversionState())

    init {
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