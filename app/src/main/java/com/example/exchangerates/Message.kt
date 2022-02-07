package com.example.exchangerates

import com.google.gson.annotations.SerializedName

data class Valutes (
    @SerializedName( "Date")
    val date: String,

    @SerializedName( "PreviousDate")
    val previousDate: String,

    @SerializedName("PreviousURL")
    val previousURL: String,

    @SerializedName( "Timestamp")
    val timestamp: String,

    @SerializedName( "Valute")
    val valutes: Map<String, Valute>
)

data class Valute (
    @SerializedName( "ID")
    val id: String,

    @SerializedName( "NumCode")
    val numCode: String,

    @SerializedName("CharCode")
    val charCode: String,

    @SerializedName( "Nominal")
    val nominal: Long,

    @SerializedName("Name")
    val name: String,

    @SerializedName("Value")
    val value: Double,

    @SerializedName("Previous")
    val previous: Double
)
