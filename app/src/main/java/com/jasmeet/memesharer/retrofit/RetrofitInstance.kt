package com.jasmeet.memesharer.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://meme-api.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val memeApi: MemeApi = retrofit.create(MemeApi::class.java)
}