package com.example.retrofitpractice2.data.network

import com.example.retrofitpractice2.data.model.CurrencyResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("daily_json.js")
    fun getCurrencyRates(): Call<CurrencyResponse>
}