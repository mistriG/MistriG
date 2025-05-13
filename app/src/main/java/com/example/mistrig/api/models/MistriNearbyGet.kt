package com.example.mistrig.api.models

import com.google.gson.annotations.SerializedName

data class MistriNearbyGet(
    @SerializedName("_id") var id: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("lat") var latitude: Number? = null,
    @SerializedName("lng") var longitude: Number? = null
): Model
