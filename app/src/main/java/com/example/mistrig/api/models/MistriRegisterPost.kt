package com.example.mistrig.api.models

import com.google.gson.annotations.SerializedName

data class MistriRegisterPost(
    @SerializedName("name") var name: String,
    @SerializedName("email") var email: String,
    @SerializedName("mobile") var mobile: String,
    @SerializedName("field_of_expertise") var fieldOfExpertise: String,
    @SerializedName("price") var price: Number,
    @SerializedName("description") var description: String,
    @SerializedName("password") var password: String
): Model
