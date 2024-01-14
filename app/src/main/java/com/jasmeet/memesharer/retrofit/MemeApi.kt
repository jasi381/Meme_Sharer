package com.jasmeet.memesharer.retrofit

import com.jasmeet.memesharer.retrofit.response.MemeResponse
import retrofit2.Call
import retrofit2.http.GET

interface MemeApi {

    @GET("gimme")
    fun getMeme():Call<MemeResponse>
}