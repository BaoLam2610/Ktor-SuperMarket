package com.lambao.data.repository.user

import com.lambao.common.EntityRepository
import com.lambao.data.dto.LoginUserBody
import com.lambao.data.entity.user.UserEntity

interface UserRepository : EntityRepository<UserEntity> {
    suspend fun findUserByPhoneAndPassword(
        entity: UserEntity
    ): UserEntity

    suspend fun isPhoneNumberExists(phone: String): Boolean
}