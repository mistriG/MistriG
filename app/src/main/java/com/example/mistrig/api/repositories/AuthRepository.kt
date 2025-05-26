package com.example.mistrig.api.repositories

import com.example.mistrig.api.util.Messages
import com.example.mistrig.api.util.Status
import com.example.mistrig.api.RetrofitInstance
import com.example.mistrig.api.models.MistriRegisterPost
import com.example.mistrig.api.models.OtpPost
import com.example.mistrig.api.models.ResetPasswordPost
import com.example.mistrig.api.models.UserLoginPost
import com.example.mistrig.api.models.UserRegisterPost
import com.example.mistrig.api.services.AuthService
import com.example.mistrig.api.util.Helper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepository {

    private val authService: AuthService =
        RetrofitInstance.getInstance().create(AuthService::class.java)
    private val helper = Helper()

    suspend fun registerUser(registerUser: UserRegisterPost, onComplete: (Status, String) -> Unit) {
        withContext(Dispatchers.IO) {
            try {

                val response = authService.registerUser(registerUser)
                helper.postResponseHandler(response, onComplete)

            } catch (e: Exception) {
                onComplete(Status.FAILED, e.localizedMessage ?: Messages.SOMETHING_WENT_WRONG)
            }
        }
    }

    suspend fun registerMistri(
        mistriRegisterPost: MistriRegisterPost,
        onComplete: (Status, String) -> Unit
    ) {
        withContext(Dispatchers.IO) {
            try {

                val response = authService.registerMistri(mistriRegisterPost)
                helper.postResponseHandler(response, onComplete)

            } catch (e: Exception) {
                onComplete(Status.FAILED, e.localizedMessage ?: Messages.SOMETHING_WENT_WRONG)
            }

        }
    }

    suspend fun loginUser(userLoginPost: UserLoginPost, onComplete: (Status, String) -> Unit) {
        withContext(Dispatchers.IO) {
            try {

                val response = authService.loginUser(userLoginPost)
                helper.postResponseHandler(response, onComplete)

            } catch (e: Exception) {
                onComplete(Status.FAILED, e.localizedMessage ?: Messages.SOMETHING_WENT_WRONG)
            }

        }
    }

    suspend fun verifyOtp(otpPost: OtpPost, onComplete: (Status, String) -> Unit) {
        withContext(Dispatchers.IO) {
            try {

                val response = authService.verifyOtp(otpPost)
                helper.postResponseHandler(response, onComplete)

            } catch (e: Exception) {
                onComplete(Status.FAILED, e.localizedMessage ?: Messages.SOMETHING_WENT_WRONG)
            }
        }
    }

    suspend fun resetPassword(
        resetPasswordPost: ResetPasswordPost,
        onComplete: (Status, String) -> Unit
    ) {
        withContext(Dispatchers.IO) {
            try {

                val response = authService.resetPassword(resetPasswordPost)
                helper.postResponseHandler(response, onComplete)

            } catch (e: Exception) {
                onComplete(Status.FAILED, e.localizedMessage ?: Messages.SOMETHING_WENT_WRONG)
            }

        }
    }
}