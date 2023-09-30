package com.lambao.data.mapper

import com.lambao.data.dto.UserDto
import com.lambao.data.entity.user.UserTable
import com.lambao.domain.model.User
import org.jetbrains.exposed.sql.ResultRow


fun ResultRow?.rowToUser(): User? {
    return if (this == null) null
    else User(
        id = this[UserTable.id],
        name = this[UserTable.name],
        phoneNumber = this[UserTable.phoneNumber],
        email = this[UserTable.email],
        password = this[UserTable.password],
        avatar = this[UserTable.avatar],
    )
}

fun UserDto.toUser() = User(
    id = null,
    name = name,
    phoneNumber = phoneNumber,
    email = email,
    password = password,
    avatar = avatar
)