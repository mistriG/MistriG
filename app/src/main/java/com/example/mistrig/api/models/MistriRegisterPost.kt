package com.example.mistrig.api.models

import com.google.gson.annotations.SerializedName

data class MistriRegisterPost(
    @SerializedName("name") var name: String,
    @SerializedName("email") var email: String,
    @SerializedName("password") var password: String,
    @SerializedName("skill") var skill: String,
    @SerializedName("phone") var phone: String
): Model
