package com.example.mistrig.api.models

import com.google.gson.annotations.SerializedName

data class FieldPost(
    @SerializedName("name") var name: String
): Model
