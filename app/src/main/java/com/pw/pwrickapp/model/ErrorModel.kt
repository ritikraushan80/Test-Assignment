package com.pw.pwrickapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Ritik on: 31/08/24
 */

data class ErrorModel(
    @SerializedName("code")
    @Expose
    var code: Int,

    @SerializedName("message")
    @Expose
    var message: String)