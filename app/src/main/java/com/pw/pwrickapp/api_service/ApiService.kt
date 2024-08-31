package com.pw.pwrickapp.api_service

import com.pw.pwrickapp.model.ApiResult
import com.pw.pwrickapp.model.Results
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path


/**
 * Created by Ritik on: 31/08/24
 */


interface ApiService {

    @Headers("Content-Type: application/json")
    @GET("character")
    suspend fun getCharacters(): ApiResult<ArrayList<Results>>

    @Headers("Content-Type: application/json")
    @GET("character/{id}")
    suspend fun getCharacterDetail(@Path("id") id: Int): Results
}
