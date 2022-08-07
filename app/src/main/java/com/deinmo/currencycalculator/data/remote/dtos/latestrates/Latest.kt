package com.deinmo.currencycalculator.data.remote.dtos.latestrates

import com.deinmo.currencycalculator.domain.models.CountryModel
import com.google.gson.annotations.SerializedName


data class Latest (

  @SerializedName("success"   ) var success   : Boolean? = null,
  @SerializedName("timestamp" ) var timestamp : Int?     = null,
  @SerializedName("base"      ) var base      : String?  = null,
  @SerializedName("date"      ) var date      : String?  = null,
  @SerializedName("rates"     ) var rates     : HashMap<String, Double>?   = null,
 // @SerializedName("rates"     ) var rates     : Rates?   = Rates()
){
  fun toCountryModel(): MutableList<CountryModel> {
    val countryModels = mutableListOf<CountryModel>()
    rates?.onEach { rate->
      var countryModel = CountryModel(rate = rate.value,symbol = rate.key)
      countryModels.add(countryModel)
    }
    return countryModels
  }
}