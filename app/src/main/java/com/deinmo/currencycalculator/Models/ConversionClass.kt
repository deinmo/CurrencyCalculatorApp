package com.deinmo.currencycalculator

import com.google.gson.annotations.SerializedName


data class ConversionClass (

  @SerializedName("success"    ) var success    : Boolean? = null,
  @SerializedName("query"      ) var query      : Query?   = Query(),
  @SerializedName("info"       ) var info       : Info?    = Info(),
  @SerializedName("historical" ) var historical : String?  = null,
  @SerializedName("date"       ) var date       : String?  = null,
  @SerializedName("result"     ) var result     : Double?  = null

)