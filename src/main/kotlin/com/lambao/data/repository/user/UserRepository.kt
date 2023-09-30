package com.lambao.data.repository.user

import com.lambao.domain.model.User

interface UserRepository {

    suspend fun register(user: User): User?
}