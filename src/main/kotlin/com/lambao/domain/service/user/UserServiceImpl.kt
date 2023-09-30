package com.lambao.domain.service.user

import com.lambao.common.BaseResponse
import com.lambao.data.dto.UserDto
import com.lambao.data.mapper.toUser
import com.lambao.data.repository.user.UserRepository

class UserServiceImpl(
    private val repository: UserRepository
) : UserService {
    override suspend fun register(params: UserDto): BaseResponse<Any> {
        return try {
            val user = params.toUser()
            val userDto = repository.register(user)
            BaseResponse.Success(data = userDto)
        } catch (e: Exception) {
            BaseResponse.Error("Da co loi xay ra")
        }
    }
}