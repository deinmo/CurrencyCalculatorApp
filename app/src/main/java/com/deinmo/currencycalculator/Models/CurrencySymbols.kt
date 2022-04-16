package com.deinmo.currencycalculator

import com.google.gson.annotations.SerializedName


data class CurrencySymbols (

  @SerializedName("success" ) var success : Boolean? = null,
  @SerializedName("symbols" ) var symbols : Symbols? = Symbols()

)