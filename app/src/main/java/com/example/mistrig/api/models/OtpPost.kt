package com.example.mistrig.api.models

import com.google.gson.annotations.SerializedName

data class OtpPost(
    @SerializedName("email") val email: String,
    @SerializedName("otp") val otp: String
): Model
