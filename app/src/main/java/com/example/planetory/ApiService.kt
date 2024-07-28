package com.example.planetory

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("quotes")
    fun getRandomQuotes(): Call<List<QuotesModel>>
}