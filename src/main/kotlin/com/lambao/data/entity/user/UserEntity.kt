package com.lambao.data.entity.user

import com.lambao.data.entity.Entity

data class UserEntity(
    override val id: Int = -1,
    val name: String? = null,
    val phoneNumber: String? = null,
    val email: String? = null,
    val password: String? = null,
    val avatar: String? = null,
): Entity(id)