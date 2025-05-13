package com.example.mistrig.api.models

import com.google.gson.annotations.SerializedName

data class MistriUserGet(
    @SerializedName("_id") var id: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("mobile") var mobile: String? = null,
    @SerializedName("field_of_expertise") var fieldOfExpertise: String? = null,
    @SerializedName("price") var price: Number? = null
): Model
