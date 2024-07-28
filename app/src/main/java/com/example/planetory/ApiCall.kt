package com.example.planetory

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiCall {
    fun getRandomQuotes(callback: (List<QuotesModel>?) -> Unit) {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://type.fit/api/")
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiService::class.java)

        api.getRandomQuotes().enqueue(object : Callback<List<QuotesModel>> {

            override fun onResponse(
                call: Call<List<QuotesModel>>,
                response: Response<List<QuotesModel>>
            ) {
                if (response.isSuccessful) {
                    val quoteslist: List<QuotesModel>? = response.body()
                    callback(quoteslist)
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<List<QuotesModel>>, t: Throwable) {
                callback(null)
            }
        })
    }
}