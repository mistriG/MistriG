package com.example.mistrig.api.models

import com.google.gson.annotations.SerializedName

data class ValidationError(
    @SerializedName("loc") var loc: List<Any>? = null,
    @SerializedName("msg") var message: String? = null,
    @SerializedName("type") var type: String? = null
): Model
