package com.example.mistrig.api.models

import com.google.gson.annotations.SerializedName

data class UserRegisterPost(
    @SerializedName("name") var name: String,
    @SerializedName("email") var email: String,
    @SerializedName("password") var password: String
): Model
