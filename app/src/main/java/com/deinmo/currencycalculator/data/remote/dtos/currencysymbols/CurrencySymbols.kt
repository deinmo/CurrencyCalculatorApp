package com.deinmo.currencycalculator.data.remote.dtos.currencysymbols

import com.deinmo.currencycalculator.domain.models.CountryModel
import com.google.gson.annotations.SerializedName


data class CurrencySymbols (

  @SerializedName("success" ) var success : Boolean? = null,
  @SerializedName("symbols" ) var symbols : HashMap<String, String>?   = null,
  //@SerializedName("symbols" ) var symbols : Symbols? = Symbols()
){
  fun toCountryModel(): MutableList<CountryModel> {
    val countryModels = mutableListOf<CountryModel>()
    symbols?.onEach { symbol->
      var countryModel = CountryModel(symbol.value,symbol.key)
      countryModels.add(countryModel)
    }
    return countryModels
  }
}