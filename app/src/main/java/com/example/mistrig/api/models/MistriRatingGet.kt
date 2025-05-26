package com.example.mistrig.api.models

import com.google.gson.annotations.SerializedName

data class MistriRatingGet(
    @SerializedName("name") val name: String? = null,
    @SerializedName("email") val email: String? = null,
    @SerializedName("ratings") val ratings: List<Number>? = null,
    @SerializedName("average_rating") val averageRating: Number? = null,
    @SerializedName("total_ratings") val totalRatings: Number? = null
): Model
