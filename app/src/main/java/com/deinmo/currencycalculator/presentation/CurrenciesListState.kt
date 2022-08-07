package com.deinmo.currencycalculator.presentation

import com.deinmo.currencycalculator.data.remote.dtos.latestrates.Rates
import com.deinmo.currencycalculator.domain.models.CountryModel

data class CurrenciesListState(
    val counrtyModels: List<CountryModel> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false
)
