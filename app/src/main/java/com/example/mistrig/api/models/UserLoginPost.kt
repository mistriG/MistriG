package com.example.mistrig.api.models

import com.google.gson.annotations.SerializedName

data class UserLoginPost(
    @SerializedName("email") var email: String,
    @SerializedName("password") var password: String
): Model
