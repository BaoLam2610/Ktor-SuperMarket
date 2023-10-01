package com.lambao.domain.service.user

import com.lambao.common.DataResponse
import com.lambao.data.dto.LoginUserBody
import com.lambao.data.dto.RegisterUserBody
import com.lambao.data.dto.UserDto

interface UserService {

    suspend fun login(body: LoginUserBody) : DataResponse<UserDto>

    suspend fun register(body: RegisterUserBody): DataResponse<UserDto>
}