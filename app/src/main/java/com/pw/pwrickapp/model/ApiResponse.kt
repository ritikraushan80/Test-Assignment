package com.pw.pwrickapp.model

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Ritik on: 31/08/24
 */

@Keep
data class ApiResponse<T>(

    @SerializedName("data")
    @Expose
    var data: T,

    @SerializedName("status")
    @Expose
    val status: String,

    @SerializedName("message")
    @Expose
    val message: String,

    @SerializedName("code")
    @Expose
    val code: Int,
)