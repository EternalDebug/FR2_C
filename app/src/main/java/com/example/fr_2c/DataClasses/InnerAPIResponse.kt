package com.example.fr_2c.DataClasses

import com.google.gson.annotations.SerializedName

data class InnerAPIResponse(
    @SerializedName("status"       ) var status       : String?             = null,

    @SerializedName("lin_sent" ) var linearSent : Double?                = null,
    @SerializedName("lin_perc" ) var linearPercent : Double?                = null,

    @SerializedName("neuro_sent" ) var neuroSent : Double?                = null,
    @SerializedName("neuro_perc" ) var neuroPercent : Double?                = null,

    @SerializedName("ai_sent" ) var aiSent : Double?                = null,
    @SerializedName("ai_perc" ) var aiPercent : Double?                = null,

    @SerializedName("res_sent" ) var resSent : Double?                = null,
    @SerializedName("res_perc" ) var resPercent : Double?                = null,

    @SerializedName("comment"       ) var comment       : String?             = null
)
