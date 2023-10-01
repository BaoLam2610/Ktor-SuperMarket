package com.lambao.domain.service

import com.lambao.common.DataResponse
import com.lambao.domain.exceptions.ErrorCode
import io.ktor.http.*
import org.jetbrains.exposed.exceptions.ExposedSQLException


open class BaseService {

    suspend fun <T> handleData(
        validation: () -> Unit = {},
        block: suspend () -> T
    ): DataResponse<T> {
        return try {
            validation()
            DataResponse.Success(statusCode = HttpStatusCode.OK, data = block())
        } catch (e: Exception) {
            handleError(e)
        }
    }

    private fun <T> handleError(e: Exception): DataResponse<T> {
        return when (e) {
            is ExposedSQLException ->
                DataResponse.Error(statusCode = HttpStatusCode.BadRequest, message = ErrorCode.DATABASE_ERROR.message)

            else ->
                DataResponse.Error(statusCode = HttpStatusCode.BadRequest, message = ErrorCode.UNKNOWN.message)
        }
    }
}