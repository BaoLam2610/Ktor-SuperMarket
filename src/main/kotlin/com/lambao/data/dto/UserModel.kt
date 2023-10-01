package com.lambao.data.dto

data class UserDto(
    val name: String,
    val phoneNumber: String,
    val email: String,
    val password: String,
    val avatar: String
)

data class LoginUserBody(
    val phone: String,
    val password: String,
)

data class RegisterUserBody(
    val name: String,
    val phoneNumber: String,
    val email: String?,
    val password: String,
    val avatar: String?
)
