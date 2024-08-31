package com.pw.pwrickapp.repository

import com.pw.pwrickapp.api_service.NetworkModule
import com.pw.pwrickapp.model.ApiResult
import com.pw.pwrickapp.model.Results


/**
 * Created by Ritik on: 31/08/24
 */

class CharacterRepository :CharacterDataRepository {

    override suspend fun getCharacterData(): ApiResult<ArrayList<Results>> {
        return NetworkModule.fitbitApi.getCharacters()
    }

    override suspend fun getCharacterDetailsData(id:Int): Results {
        return NetworkModule.fitbitApi.getCharacterDetail(id)
    }


}
