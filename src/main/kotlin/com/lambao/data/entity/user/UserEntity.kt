package com.lambao.data.entity.user

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

class UserEntity() : Table("user") {
    val id: Column<Int> = integer("id").autoIncrement()
    val name: Column<String> = text("name")
    val phoneNumber: Column<String> = text("phone_number")
    val email: Column<String> = text("email")
    val password: Column<String> = text("password")
    val avatar: Column<String> = text("avatar")
    val createdAt: Column<LocalDateTime> = datetime("created_at").clientDefault { LocalDateTime.now() }
    val updatedAt: Column<LocalDateTime> = datetime("updated_at").clientDefault { LocalDateTime.now() }
    val deletedAt: Column<LocalDateTime> = datetime("deleted_at").clientDefault { LocalDateTime.now() }

    override val primaryKey: PrimaryKey
        get() = PrimaryKey(UserTable.id)
}
