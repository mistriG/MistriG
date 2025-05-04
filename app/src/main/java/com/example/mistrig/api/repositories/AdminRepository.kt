package com.example.mistrig.api.repositories

import com.example.mistrig.api.models.FieldPost
import com.example.mistrig.api.RetrofitInstance
import com.example.mistrig.api.models.BookingAdminGet
import com.example.mistrig.api.models.FieldGet
import com.example.mistrig.api.models.MistriAdminGet
import com.example.mistrig.api.models.UserAdminGet
import com.example.mistrig.api.services.AdminService
import com.example.mistrig.api.util.Helper
import com.example.mistrig.api.util.Messages
import com.example.mistrig.api.util.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AdminRepository {

    private val adminService: AdminService =
        RetrofitInstance.getInstance().create(AdminService::class.java)
    private val helper = Helper()

    suspend fun addField(fieldPost: FieldPost, onComplete: (Status, String) -> Unit) {
        withContext(Dispatchers.IO) {
            try {

                val response = adminService.addField(fieldPost)
                helper.postResponseHandler(response, onComplete)

            } catch (e: Exception) {
                onComplete(Status.FAILED, e.localizedMessage ?: Messages.SOMETHING_WENT_WRONG)
            }
        }
    }

    suspend fun getFields(onComplete: (List<FieldGet>?, Status, String) -> Unit) {
        withContext(Dispatchers.IO) {
            try {

                val response = adminService.getFields()

                helper.getAnyModelsList(response) { list, status, message ->
                    onComplete(list, status, message)
                }

            } catch (e: Exception) {
                onComplete(
                    null,
                    Status.FAILED,
                    e.localizedMessage ?: Messages.SOMETHING_WENT_WRONG
                )
            }
        }
    }

    suspend fun getAllUsers(onComplete: (List<UserAdminGet>?, Status, String) -> Unit) {
        withContext(Dispatchers.IO) {
            try {

                val response = adminService.getAllUsers()

                helper.getAnyModelsList(response) { list, status, message ->
                    onComplete(list, status, message)
                }

            } catch (e: Exception) {
                onComplete(null, Status.FAILED, e.localizedMessage ?: Messages.SOMETHING_WENT_WRONG)
            }
        }
    }

    suspend fun getAllMistris(onComplete: (List<MistriAdminGet>?, Status, String) -> Unit) {
        withContext(Dispatchers.IO) {
            try {

                val response = adminService.getAllMistris()

                helper.getAnyModelsList(response) { list, status, message ->
                    onComplete(list, status, message)
                }

            } catch (e: Exception) {
                onComplete(null, Status.FAILED, e.localizedMessage ?: Messages.SOMETHING_WENT_WRONG)
            }
        }
    }

    suspend fun getAllBookings(onComplete: (List<BookingAdminGet>?, Status, String) -> Unit) {
        withContext(Dispatchers.IO) {
            try {

                val response = adminService.getAllBookings()

                helper.getAnyModelsList(response) { list, status, message ->
                    onComplete(list, status, message)
                }

            } catch (e: Exception) {
                onComplete(null, Status.FAILED, e.localizedMessage ?: Messages.SOMETHING_WENT_WRONG)
            }
        }
    }
}