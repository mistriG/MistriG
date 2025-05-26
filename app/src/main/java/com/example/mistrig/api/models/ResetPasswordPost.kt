package com.example.mistrig.api.models

import com.google.gson.annotations.SerializedName

data class ResetPasswordPost(
    @SerializedName("email") val email: String,
    @SerializedName("new_password") val newPassword: String
): Model
