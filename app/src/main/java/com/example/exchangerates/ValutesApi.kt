package com.example.exchangerates

import retrofit2.Call
import retrofit2.http.GET

interface ValutesApi {
    @GET("daily_json.js")
    fun getValutes(): Call<Valutes?>?
}