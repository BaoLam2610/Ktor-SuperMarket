package com.lambao.data.repository.user

import com.lambao.data.DatabaseFactory.query
import com.lambao.data.entity.user.UserTable
import com.lambao.data.mapper.rowToUser
import com.lambao.domain.model.User
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.statements.InsertStatement
import java.time.LocalDateTime

class UserRepositoryImpl : UserRepository {
    override suspend fun register(user: User): User? {
        var statement: InsertStatement<Number>? = null
        query {
            statement = UserTable.insert {
                it[name] = user.name
                it[phoneNumber] = user.phoneNumber
                it[email] = user.email
                it[password] = user.password
                user.avatar?.let { userAvatar ->
                    it[avatar] = userAvatar
                }
                it[createdAt] = LocalDateTime.now()
            }
        }
        return statement?.resultedValues?.firstOrNull()?.rowToUser()
    }
}