package com.deinmo.currencycalculator.domain.models

import android.graphics.drawable.Drawable

data class CountryModel(
    val countryName: String? = null,
    val symbol: String? = null,
    val image: Drawable? = null,
    val rate: Int? = null
)
