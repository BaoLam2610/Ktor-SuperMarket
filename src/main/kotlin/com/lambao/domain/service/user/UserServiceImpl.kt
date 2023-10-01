package com.lambao.domain.service.user

import com.lambao.common.DataResponse
import com.lambao.data.dto.LoginUserBody
import com.lambao.data.dto.RegisterUserBody
import com.lambao.data.dto.UserDto
import com.lambao.data.mapper.toUserDto
import com.lambao.data.mapper.toUserEntity
import com.lambao.data.repository.user.UserRepository
import com.lambao.domain.service.BaseService
import com.lambao.domain.usecase.ValidateLoginUseCase
import com.lambao.domain.usecase.ValidateRegisterUseCase

class UserServiceImpl(
    private val repository: UserRepository,
    private val validateLoginUseCase: ValidateLoginUseCase,
    private val validateRegisterUseCase: ValidateRegisterUseCase
) : UserService, BaseService() {

    override suspend fun login(body: LoginUserBody): DataResponse<UserDto> = handleData({
        validateLoginUseCase.invoke(body)
    }) {
        repository.findUserByPhoneAndPassword(body.toUserEntity()).toUserDto()
    }

    override suspend fun register(body: RegisterUserBody): DataResponse<UserDto> = handleData({
        validateRegisterUseCase.invoke(body)
    }) {
        repository.insert(body.toUserEntity()).toUserDto()
    }
}