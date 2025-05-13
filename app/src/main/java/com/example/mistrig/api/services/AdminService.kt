package com.example.mistrig.api.services

import com.example.mistrig.api.models.BookingAdminGet
import com.example.mistrig.api.models.FieldGet
import com.example.mistrig.api.models.FieldPost
import com.example.mistrig.api.models.MistriAdminGet
import com.example.mistrig.api.models.UserAdminGet
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AdminService {

    @POST("admin/add")
    suspend fun addField(@Body fieldPost: FieldPost): Response<ResponseBody>

    @GET("/admin")
    suspend fun getFields(): Response<List<FieldGet>>

    @GET("/admin/admin/users")
    suspend fun getAllUsers(): Response<List<UserAdminGet>>

    @GET("admin/mistri/all")
    suspend fun getAllMistris(): Response<List<MistriAdminGet>>

    @GET("admin/admin/bookings")
    suspend fun getAllBookings(): Response<List<BookingAdminGet>>
}