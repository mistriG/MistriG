package com.example.mistrig.api.repositories

import com.example.mistrig.api.RetrofitInstance
import com.example.mistrig.api.models.FieldGet
import com.example.mistrig.api.models.MistriRatingGet
import com.example.mistrig.api.models.MistriAvailabilityPost
import com.example.mistrig.api.models.MistriLocationPost
import com.example.mistrig.api.services.MistriService
import com.example.mistrig.api.util.Helper
import com.example.mistrig.api.util.Messages
import com.example.mistrig.api.util.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MistriRepository {

    private val mistriService: MistriService =
        RetrofitInstance.getInstance().create(MistriService::class.java)
    private val helper = Helper()

    suspend fun getFields(onComplete: (List<FieldGet>?, Status, String) -> Unit) {
        withContext(Dispatchers.IO) {
            try {

                val response = mistriService.getFields()

                helper.getAnyModelsList(response) { list, status, message ->
                    onComplete(list, status, message)
                }

            } catch (e: Exception) {
                onComplete(null, Status.FAILED, e.localizedMessage ?: Messages.SOMETHING_WENT_WRONG)
            }
        }
    }

    suspend fun updateMistriLocation(
        mistriLocationPost: MistriLocationPost,
        onComplete: (Status, String) -> Unit
    ) {
        withContext(Dispatchers.IO) {
            try {

                val response = mistriService.updateMistriLocation(mistriLocationPost)
                helper.postResponseHandler(response, onComplete)

            } catch (e: Exception) {
                onComplete(Status.FAILED, e.localizedMessage ?: Messages.SOMETHING_WENT_WRONG)
            }
        }
    }

    suspend fun updateAvailability(
        mistriAvailabilityPost: MistriAvailabilityPost,
        onComplete: (Status, String) -> Unit
    ) {
        withContext(Dispatchers.IO) {
            try {

                val response = mistriService.updateAvailability(mistriAvailabilityPost)
                helper.postResponseHandler(response, onComplete)

            } catch (e: Exception) {
                onComplete(Status.FAILED, e.localizedMessage ?: Messages.SOMETHING_WENT_WRONG)
            }
        }
    }

    suspend fun getMistriRating(
        email: String,
        onComplete: (MistriRatingGet?, Status, String) -> Unit
    ){
        withContext(Dispatchers.IO) {
            try {

                val response = mistriService.getMistriRating(email)

                helper.getAnyModel(response) { model, status, message ->
                    onComplete(model, status, message)
                }

            } catch (e: Exception) {
                onComplete(null, Status.FAILED, e.localizedMessage ?: Messages.SOMETHING_WENT_WRONG)
            }
        }
    }
}