package com.example.mistrig.api.repositories

import com.example.mistrig.api.util.Messages
import com.example.mistrig.api.util.Status
import com.example.mistrig.api.RetrofitInstance
import com.example.mistrig.api.models.MistriBookPost
import com.example.mistrig.api.models.MistriLocationGet
import com.example.mistrig.api.models.MistriNearbyGet
import com.example.mistrig.api.models.MistriRatingGet
import com.example.mistrig.api.models.MistriRatingPost
import com.example.mistrig.api.models.MistriUserGet
import com.example.mistrig.api.services.UserService
import com.example.mistrig.api.util.Helper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository {

    private val userService: UserService =
        RetrofitInstance.getInstance().create(UserService::class.java)
    private val helper = Helper()

    suspend fun searchMistri(
        area: String? = null,
        expertise: String? = null,
        onComplete: (List<MistriUserGet>?, Status, String) -> Unit
    ) {

        withContext(Dispatchers.IO) {
            try {

                val response = userService.searchMistri(area, expertise)

                helper.getAnyModelsList(response) { list, status, message ->
                    onComplete(list, status, message)
                }

            } catch (e: Exception) {
                onComplete(null, Status.FAILED, e.localizedMessage ?: Messages.SOMETHING_WENT_WRONG)
            }
        }

    }

    suspend fun getAllMistriLocation(
        onComplete: (List<MistriLocationGet>?, Status, String) -> Unit
    ) {
        withContext(Dispatchers.IO) {
            try {

                val response = userService.getAllMistriLocation()

                helper.getAnyModelsList(response) { list, status, message ->
                    onComplete(list, status, message)
                }

            } catch (e: Exception) {
                onComplete(null, Status.FAILED, e.localizedMessage ?: Messages.SOMETHING_WENT_WRONG)
            }
        }
    }

    suspend fun getNearbyMistris(
        latitude: Number, longitude: Number, radiusKm: Number? = null,
        onComplete: (List<MistriNearbyGet>?, Status, String) -> Unit
    ) {
        withContext(Dispatchers.IO) {
            try {

                val response = userService.getNearbyMistris(latitude, longitude, radiusKm)

                helper.getAnyModelsList(response) { list, status, message ->
                    onComplete(list, status, message)
                }

            } catch (e: Exception) {
                onComplete(null, Status.FAILED, e.localizedMessage ?: Messages.SOMETHING_WENT_WRONG)
            }
        }
    }

    suspend fun bookMistri(
        mistriBookPost: MistriBookPost,
        onComplete: (Status, String) -> Unit
    ) {
        withContext(Dispatchers.IO) {
            try {

                val response = userService.bookMistri(mistriBookPost)
                helper.postResponseHandler(response, onComplete)

            } catch (e: Exception) {
                onComplete(Status.FAILED, e.localizedMessage ?: Messages.SOMETHING_WENT_WRONG)
            }
        }
    }

    suspend fun rateMistri(
        mistriRatingPost: MistriRatingPost,
        onComplete: (Status, String) -> Unit
    ) {
        withContext(Dispatchers.IO) {
            try {

                val response = userService.rateMistri(mistriRatingPost)

                helper.postResponseHandler(response, onComplete)

            } catch (e: Exception) {
                onComplete(Status.FAILED, e.localizedMessage ?: Messages.SOMETHING_WENT_WRONG)
            }
        }
    }

    suspend fun getMistriRating(
        email: String,
        onComplete: (MistriRatingGet?, Status, String) -> Unit
    ) {
        withContext(Dispatchers.IO) {
            try {

                val response = userService.getMistriRating(email)

                helper.getAnyModel(response) { model, status, message ->
                    onComplete(model, status, message)
                }

            } catch (e: Exception) {
                onComplete(null, Status.FAILED, e.localizedMessage ?: Messages.SOMETHING_WENT_WRONG)
            }
        }
    }

}