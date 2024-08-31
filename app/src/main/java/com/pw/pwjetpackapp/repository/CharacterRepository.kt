package com.pw.pwjetpackapp.repository

import com.pw.pwjetpackapp.api_service.ApiService
import com.pw.pwjetpackapp.api_service.NetworkModule
import com.pw.pwjetpackapp.model.CharacterData
import com.pw.pwjetpackapp.model.Results
import retrofit2.Response


/**
 * Created by Ritik on: 31/08/24
 */

class CharacterRepository {

    suspend fun getCharacters(): Response<CharacterData> {
        return  NetworkModule.apiService.getCharacters()
    }

    suspend fun getCharacterDetail(id: Int) : Response<Results> {
        return NetworkModule.apiService.getCharacterDetail(id)
    }
}
