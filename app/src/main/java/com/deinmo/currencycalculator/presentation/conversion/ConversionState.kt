package com.deinmo.currencycalculator.presentation.conversion

import com.deinmo.currencycalculator.domain.models.CountryModel

data class ConversionState(
    val counrtyModels: List<CountryModel> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false
)
