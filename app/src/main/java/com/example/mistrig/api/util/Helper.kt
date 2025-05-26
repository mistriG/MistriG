package com.example.mistrig.api.util

import com.example.mistrig.api.models.HTTPValidationError
import com.example.mistrig.api.models.Model
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Response

class Helper {

    fun <T: Model> getAnyModel(
        response: Response<T>,
        onComplete: (T?, Status, String) -> Unit
    ) {
        if (response.code() == 200) {
            val model = response.body()
            onComplete(
                model,
                Status.SUCCESS,
                Messages.TASK_COMPLETED_SUCCESSFULLY
            )
        } else {
            onComplete(null, Status.FAILED, response.toString())
        }
    }

    fun <T : Model> getAnyModelsList(
        response: Response<List<T>>,
        onComplete: (List<T>?, Status, String) -> Unit
    ) {

        if (response.code() == 200) {
            val list = response.body()
            onComplete(
                list,
                Status.SUCCESS,
                Messages.TASK_COMPLETED_SUCCESSFULLY
            )
        } else {
            onComplete(null, Status.FAILED, response.toString())
        }

    }

    fun postResponseHandler(
        response: Response<ResponseBody>,
        onComplete: (Status, String) -> Unit
    ) {
        val responseString = response.body()?.toString()
        val gson = Gson()

        if (response.code() == 200) {
            onComplete(Status.SUCCESS, responseString ?: Messages.SUCCESS)
        } else if (response.code() == 422) {

            val httpValidationError =
                gson.fromJson(responseString, HTTPValidationError::class.java)

            onComplete(
                Status.FAILED,
                httpValidationError.toString()
            )
        } else {
            onComplete(Status.FAILED, responseString.toString())
        }
    }
}