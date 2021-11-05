package com.emptycoder.ja1.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiClient{
    val BASE_URL = "https://newsapi.org/v2/"

    @Provides
    @Singleton
    fun getApiInterface(retrofit: Retrofit):ApiInterface{
        return  retrofit.create(ApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun getApiModule() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}