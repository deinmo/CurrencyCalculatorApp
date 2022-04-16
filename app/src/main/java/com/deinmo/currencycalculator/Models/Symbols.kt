package com.deinmo.currencycalculator

import com.google.gson.annotations.SerializedName


data class Symbols (
  @SerializedName("AED" ) var AED : String? = null,
  @SerializedName("AFN" ) var AFN : String? = null,
  @SerializedName("ALL" ) var ALL : String? = null,
  @SerializedName("AMD" ) var AMD : String? = null
)