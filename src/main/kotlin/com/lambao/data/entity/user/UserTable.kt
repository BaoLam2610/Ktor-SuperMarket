package com.lambao.data.entity.user

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object UserTable : Table("user") {
    val id = integer("id").autoIncrement()
    val name = text("name")
    val phoneNumber = text("phone_number")
    val email = text("email",)
    val password = text("password")
    val avatar = text("avatar")
    val createdAt = datetime("created_at")//.clientDefault { LocalDateTime.now() }
    val updatedAt = datetime("updated_at")//.clientDefault { LocalDateTime.now() }
    val deletedAt = datetime("deleted_at")//.clientDefault { LocalDateTime.now() }

    override val primaryKey: PrimaryKey
        get() = PrimaryKey(id)
}