package com.example.mistrig.api.services

import com.example.mistrig.api.models.MistriRegisterPost
import com.example.mistrig.api.models.OtpPost
import com.example.mistrig.api.models.ResetPasswordPost
import com.example.mistrig.api.models.UserLoginPost
import com.example.mistrig.api.models.UserRegisterPost
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("/register")
    suspend fun registerUser(
        @Body userRegisterPost: UserRegisterPost
    ): Response<ResponseBody>

    @POST("/register-mistri")
    suspend fun registerMistri(
        @Body mistriRegisterPost: MistriRegisterPost
    ): Response<ResponseBody>

    @POST("/login")
    suspend fun loginUser(
        @Body userLoginPost: UserLoginPost
    ): Response<ResponseBody>

    @POST("verify-otp")
    suspend fun verifyOtp(
        @Body otpPost: OtpPost
    ): Response<ResponseBody>

    @POST("/reset_password")
    suspend fun resetPassword(
        @Body resetPasswordPost: ResetPasswordPost
    ): Response<ResponseBody>
}