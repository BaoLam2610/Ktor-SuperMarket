package com.lambao.common

interface EntityRepository<T> {
    suspend fun getAll(): List<T>
    suspend fun findById(id: Int): T?
    suspend fun find(predicate: (T) -> Boolean): T?
    suspend fun insert(entity: T): T
    suspend fun update(entity: T): T
    suspend fun delete(id: Int)
    suspend fun replace(item: T)
}
