package com.lambao.extensions

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.statements.UpdateBuilder

fun <S,T> UpdateBuilder<T>.setValue(column: Column<S>, value: S?) {
    if (value == null) return
    set(column, value)
}