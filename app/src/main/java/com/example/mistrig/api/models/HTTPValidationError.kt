package com.example.mistrig.api.models

import com.google.gson.annotations.SerializedName

data class HTTPValidationError(
    @SerializedName("details") var details: List<ValidationError>? = null
): Model
