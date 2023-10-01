package com.lambao.common

import io.ktor.http.*

sealed class DataResponse<T>(
    open val statusCode: HttpStatusCode = HttpStatusCode.OK
) {
    data class Success<T>(
        override val statusCode: HttpStatusCode,
        val message: String? = null,
        val data: T? = null
    ) : DataResponse<T>(statusCode)

    data class Error<T>(
        override val statusCode: HttpStatusCode,
        val message: String? = null,
    ) : DataResponse<T>(statusCode)
}