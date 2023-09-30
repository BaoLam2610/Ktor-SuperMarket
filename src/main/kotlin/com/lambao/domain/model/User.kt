package com.lambao.domain.model

data class User(
    val id: Int? = null,
    val name: String,
    val phoneNumber: String,
    val email: String,
    val password: String,
    val avatar: String?,
)