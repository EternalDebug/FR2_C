package com.example.example

import com.google.gson.annotations.SerializedName


data class Response (

  @SerializedName("status"       ) var status       : String?             = null,
  @SerializedName("totalResults" ) var totalResults : Int?                = null,
  @SerializedName("results"     ) var articles     : ArrayList<Articles> = arrayListOf()

)