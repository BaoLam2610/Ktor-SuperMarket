package com.lambao.extensions

fun String.isNumber() : Boolean = this.toIntOrNull() != null