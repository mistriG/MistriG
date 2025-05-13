package com.example.mistrig.api.models

import com.google.gson.annotations.SerializedName

data class MistriBookPost(
    @SerializedName("user_email") var userEmail: String,
    @SerializedName("mistri_email") var mistriEmail: String,
    @SerializedName("date") var date: String,
    @SerializedName("time") var time: String,
    @SerializedName("location") var location: String
): Model
