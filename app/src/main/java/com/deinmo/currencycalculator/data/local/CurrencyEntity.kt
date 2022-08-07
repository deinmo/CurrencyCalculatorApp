package com.deinmo.currencycalculator.data.local

import android.graphics.drawable.Drawable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.deinmo.currencycalculator.domain.models.CountryModel

@Entity
data class CurrencyEntity(
    @PrimaryKey val id: Int? = null,
    val countryname: String?,
    val symbol: String?,
    val image: Int?,
    val rate: Double?
){
    fun toCountryModel(): CountryModel {
        return CountryModel(
            countryname,
            symbol, image, rate
        )
    }
}
