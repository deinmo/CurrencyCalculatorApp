package com.deinmo.currencycalculator.data.remote.dtos.currencysymbols

import com.google.gson.annotations.SerializedName


data class CurrencySymbols (

  @SerializedName("success" ) var success : Boolean? = null,
  @SerializedName("symbols" ) var symbols : Symbols? = Symbols()

)