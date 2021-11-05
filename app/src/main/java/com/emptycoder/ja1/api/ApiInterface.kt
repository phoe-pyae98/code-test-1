package com.emptycoder.ja1.api

import com.emptycoder.ja1.model.New
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("everything")
    fun getEverthing(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String
    ): Call<New>
}