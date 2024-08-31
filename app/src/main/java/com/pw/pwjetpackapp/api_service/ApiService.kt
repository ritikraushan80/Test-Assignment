package com.pw.pwjetpackapp.api_service

import com.pw.pwjetpackapp.model.CharacterData
import com.pw.pwjetpackapp.model.Results
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created by Ritik on: 31/08/24
 */

interface ApiService {

    @GET("character")
    suspend fun getCharacters(): Response<CharacterData>

    @GET("character/{id}")
    suspend fun getCharacterDetail(@Path("id") id: Int): Response<Results>
}

