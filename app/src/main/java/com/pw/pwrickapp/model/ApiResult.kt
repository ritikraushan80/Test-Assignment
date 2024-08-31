package com.pw.pwrickapp.model

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Ritik on: 31/08/24
 */

@Keep
data class ApiResult<T>(

    @SerializedName("data")
    @Expose
    var data: T,

    @SerializedName("results")
    @Expose
    val results: T,

    @SerializedName("message")
    @Expose
    val message: String,

    @SerializedName("statusCode")
    @Expose
    val statusCode: Int,
)