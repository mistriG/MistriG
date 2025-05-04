package com.example.mistrig.api.models

import com.google.gson.annotations.SerializedName

data class BookingAdminGet(
    @SerializedName("_id") var id: String? = null,
    @SerializedName("user_email") var userEmail: String? = null,
    @SerializedName("mistri_email") var mistriEmail: String? = null,
    @SerializedName("date") var date: String? = null,
    @SerializedName("time") var time: String? = null,
    @SerializedName("location") var location: String? = null
): Model
