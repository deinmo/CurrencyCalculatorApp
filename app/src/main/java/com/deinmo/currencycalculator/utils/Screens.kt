package com.deinmo.currencycalculator.utils

sealed class Screens(val route: String){
    object ConversionScreen: Screens("conversion_screen")
    object CurrencyListScreen: Screens("currency_list")
}
