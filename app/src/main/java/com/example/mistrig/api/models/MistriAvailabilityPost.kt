package com.example.mistrig.api.models

import com.google.gson.annotations.SerializedName

data class MistriAvailabilityPost(
    @SerializedName("email") val name: String? = null,
    @SerializedName("available") val available: Boolean? = null
): Model
