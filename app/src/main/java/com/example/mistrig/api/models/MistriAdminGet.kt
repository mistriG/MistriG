package com.example.mistrig.api.models

import com.google.gson.annotations.SerializedName

data class MistriAdminGet(
    @SerializedName("name") var name: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("mobile") var mobile: String? = null,
    @SerializedName("expertise") var expertise: List<String?>? = null,
    @SerializedName("price_per_day") var pricePerDay: Number? = null,
    @SerializedName("description") var description: String? = null
): Model
