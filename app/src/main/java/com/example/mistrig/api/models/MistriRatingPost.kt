package com.example.mistrig.api.models

import com.google.gson.annotations.SerializedName

data class MistriRatingPost(
    @SerializedName("email") val email: String,
    @SerializedName("rating") val rating: Number,
): Model
