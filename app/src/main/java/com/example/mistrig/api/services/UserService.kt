package com.example.mistrig.api.services

import com.example.mistrig.api.models.MistriBookPost
import com.example.mistrig.api.models.MistriLocationGet
import com.example.mistrig.api.models.MistriNearbyGet
import com.example.mistrig.api.models.MistriRatingGet
import com.example.mistrig.api.models.MistriRatingPost
import com.example.mistrig.api.models.MistriUserGet
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {

    @GET("/user/mistri/search")
    suspend fun searchMistri(
        @Query("area") area: String? = null,
        @Query("expertise") expertise: String? = null
    ): Response<List<MistriUserGet>>

    @GET("/user/mistri/locations")
    suspend fun getAllMistriLocation(): Response<List<MistriLocationGet>>

    @GET("/user/mistri/nearby")
    suspend fun getNearbyMistris(
        @Query("lat") latitude: Number,
        @Query("lng") longitude: Number,
        @Query("radius_km") radiusKm: Number? = null
    ): Response<List<MistriNearbyGet>>

    @POST("/user/booking")
    suspend fun bookMistri(
        @Body mistriBookPost: MistriBookPost
    ): Response<ResponseBody>

    @POST("/user/mistri/rate")
    suspend fun rateMistri(
        @Body mistriRatingPost: MistriRatingPost
    ): Response<ResponseBody>

    @GET("/user/mistri/ratings/{email}")
    suspend fun getMistriRating(
        @Path("email") email: String
    ): Response<MistriRatingGet>
}