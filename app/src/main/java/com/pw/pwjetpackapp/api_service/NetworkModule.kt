package com.pw.pwjetpackapp.api_service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Ritik on: 31/08/24
 */

object NetworkModule {
    private const val BASE_URL = "https://rickandmortyapi.com/api/"
    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
