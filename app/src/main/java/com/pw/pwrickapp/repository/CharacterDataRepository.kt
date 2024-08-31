package com.pw.pwrickapp.repository

import com.pw.pwrickapp.model.ApiResult
import com.pw.pwrickapp.model.Results


/**
 * Created by Ritik on: 31/08/24
 */

interface CharacterDataRepository {

    /**
     * Gets character list data.
     */
    suspend fun getCharacterData(): ApiResult<ArrayList<Results>>

    /**
     * Gets character details data.
     */
    suspend fun getCharacterDetailsData(id:Int): Results

}
