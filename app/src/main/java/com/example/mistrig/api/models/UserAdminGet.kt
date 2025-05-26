package com.example.mistrig.api.models

import com.google.gson.annotations.SerializedName

data class UserAdminGet(
    @SerializedName("_id") var id: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("role") var role: String? = null
): Model
