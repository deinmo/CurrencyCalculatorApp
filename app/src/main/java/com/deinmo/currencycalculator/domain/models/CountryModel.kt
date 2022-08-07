package com.deinmo.currencycalculator.domain.models

import android.graphics.drawable.Drawable

data class CountryModel(
    var countryName: String? = null,
    var symbol: String? = null,
    var image: Int? = null,
    var rate: Double? = null
)
