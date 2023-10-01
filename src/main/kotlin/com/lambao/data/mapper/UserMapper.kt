package com.lambao.data.mapper

import com.lambao.data.dto.LoginUserBody
import com.lambao.data.dto.RegisterUserBody
import com.lambao.data.dto.UserDto
import com.lambao.data.entity.user.UserEntity
import com.lambao.data.entity.user.UserTable
import org.jetbrains.exposed.sql.ResultRow


fun ResultRow.rowToUser(): UserEntity {
    return UserEntity(
        id = this[UserTable.id],
        name = this[UserTable.name],
        phoneNumber = this[UserTable.phoneNumber],
        email = this[UserTable.email],
        password = this[UserTable.password],
        avatar = this[UserTable.avatar],
    )
}

fun UserDto.toUser() = UserEntity(
    id = 0,
    name = name,
    phoneNumber = phoneNumber,
    email = email,
    password = password,
    avatar = avatar
)

fun UserEntity.toUserDto() = UserDto(
    name ?: "",
    phoneNumber ?: "",
    email ?: "",
    password ?: "",
    avatar ?: ""
)

fun LoginUserBody.toUserEntity() = UserEntity(
    phoneNumber = phone,
    password = password
)

fun RegisterUserBody.toUserEntity() = UserEntity(
    name = name,
    phoneNumber = phoneNumber,
    email = email,
    password = password,
    avatar = avatar
)