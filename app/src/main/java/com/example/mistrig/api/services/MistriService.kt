package com.example.mistrig.api.services


import com.example.mistrig.api.models.FieldGet
import com.example.mistrig.api.models.MistriAvailabilityPost
import com.example.mistrig.api.models.MistriLocationPost
import com.example.mistrig.api.models.MistriRatingGet
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MistriService {

    @GET("/mistri")
    suspend fun getFields(): Response<List<FieldGet>>

    @POST("/mistri/mistri/update-location")
    suspend fun updateMistriLocation(
        @Body mistriLocationPost: MistriLocationPost
    ): Response<ResponseBody>

    @POST("/mistri/mistri/availability")
    suspend fun updateAvailability(
        @Body mistriAvailabilityPost: MistriAvailabilityPost
    ): Response<ResponseBody>

    @GET("/mistri/mistri/ratings/{email}")
    suspend fun getMistriRating(
        @Path("email") email: String
    ): Response<MistriRatingGet>
}