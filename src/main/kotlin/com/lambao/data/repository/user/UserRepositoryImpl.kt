package com.lambao.data.repository.user

import com.lambao.data.DatabaseFactory.query
import com.lambao.data.entity.user.UserEntity
import com.lambao.data.entity.user.UserTable
import com.lambao.data.mapper.rowToUser
import com.lambao.domain.exceptions.ErrorCode
import com.lambao.domain.exceptions.QueryException
import com.lambao.extensions.setValue
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.update
import java.time.LocalDateTime

class UserRepositoryImpl : UserRepository {
    override suspend fun findUserByPhoneAndPassword(entity: UserEntity): UserEntity {
        if (!isPhoneNumberExists(entity.phoneNumber!!))
            throw QueryException(ErrorCode.PHONE_EXISTS.message)
        var user: UserEntity? = null
        query {
            user = UserTable.select {
                (UserTable.phoneNumber eq entity.phoneNumber) and
                        (UserTable.password eq entity.password!!)
            }.map { it.rowToUser() }
                .firstOrNull()
        }
        if (user == null) throw Exception(ErrorCode.ACCOUNT_EXISTS.message)
        return user!!
    }

    override suspend fun isPhoneNumberExists(phone: String): Boolean {
        return query {
            !UserTable.select {
                UserTable.phoneNumber eq phone
            }.empty()
        }
    }

    override suspend fun getAll(): List<UserEntity> {
        return query {
            UserTable.select {
                UserTable.deletedAt.isNull()
            }.map { it.rowToUser() }
        }
    }

    override suspend fun findById(id: Int): UserEntity? {
        return query {
            UserTable.select { UserTable.id eq id }
                .map { it.rowToUser() }
                .singleOrNull()
        }
    }

    override suspend fun find(predicate: (UserEntity) -> Boolean): UserEntity? {
        TODO("Not yet implemented")
    }

    override suspend fun insert(entity: UserEntity): UserEntity {
        if (isPhoneNumberExists(entity.phoneNumber!!))
            throw QueryException(ErrorCode.PHONE_EXISTS.message)
        var statement: InsertStatement<Number>? = null
        query {
            statement = UserTable.insert {
                it.setValue(name, entity.name)
                it.setValue(phoneNumber, entity.phoneNumber)
                it.setValue(email, entity.email)
                it.setValue(password, entity.password)
                it.setValue(avatar, entity.avatar)
                it[createdAt] = LocalDateTime.now()
            }
        }

        return statement?.resultedValues?.firstOrNull()?.rowToUser()
            ?: throw QueryException(ErrorCode.UNKNOWN.message)
    }

    override suspend fun update(entity: UserEntity): UserEntity {
        query {
            UserTable.update({
                UserTable.id eq entity.id
            }) {
                it.setValue(name, entity.name)
                it.setValue(phoneNumber, entity.phoneNumber)
                it.setValue(email, entity.email)
                it.setValue(password, entity.password)
                it.setValue(avatar, entity.avatar)
                it[updatedAt] = LocalDateTime.now()
            }
        }
        return findById(entity.id) ?: throw QueryException(ErrorCode.UNKNOWN.message)
    }

    override suspend fun delete(id: Int) {
        query {
            UserTable.update({
                UserTable.id eq id
            }) {
                it[deletedAt] = LocalDateTime.now()
            }
        }
        /*query {
            UserTable.deleteWhere {
                UserTable.id eq id
            }
        }*/
    }

    override suspend fun replace(item: UserEntity) {

    }
}