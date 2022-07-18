package com.deinmo.currencycalculator.data

interface CurrencyRepository {
    suspend fun getsymbols()

    suspend fun getrates()

    suspend fun images()
}