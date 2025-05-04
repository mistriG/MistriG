package com.example.mistrig.api.models

import com.google.gson.annotations.SerializedName

data class FieldGet(
    @SerializedName("name") var name: String? = null
): Model
