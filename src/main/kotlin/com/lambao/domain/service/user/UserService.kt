package com.lambao.domain.service.user

import com.lambao.common.BaseResponse
import com.lambao.data.dto.UserDto

interface UserService {

    suspend fun register(params: UserDto): BaseResponse<Any>
}