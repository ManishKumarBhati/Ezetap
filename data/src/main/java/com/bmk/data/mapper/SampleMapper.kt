package com.bmk.data.mapper

import com.bmk.data.data.DataResponse
import com.bmk.domain.ItemList
import com.bmk.domain.Response
import com.bmk.domain.Result
import retrofit2.Response as ApiResponse

fun ApiResponse<DataResponse>.mapper(): Result<Response> {
    try {
        val response = this
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                val data = body.transform()
                return Result.Success(data)
            }
        }
        return Result.Failure(Exception(" ${response.code()} ${response.message()}"))
    } catch (e: Exception) {
        return Result.Failure(Exception((e.message ?: e.toString())))
    }
}

fun DataResponse.transform(): Response {
    return Response(
        uidata = this.uidata.map {
            ItemList(
                value = it.value,
                key = it.key,
                hint = it.hint,
                uitype = it.uitype
            )
        },
        logoUrl = logoUrl,
        headingText = headingText
    )
}