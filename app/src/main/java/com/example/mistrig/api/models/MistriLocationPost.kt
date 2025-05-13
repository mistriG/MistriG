package com.example.mistrig.api.models

import com.google.gson.annotations.SerializedName

data class MistriLocationPost(
    @SerializedName("email") val name: String,
    @SerializedName("latitude") val latitude: Number,
    @SerializedName("longitude") val longitude: Number
): Model
