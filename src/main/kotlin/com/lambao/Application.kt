package com.lambao

import com.lambao.data.DatabaseFactory
import com.lambao.data.repository.user.UserRepository
import com.lambao.data.repository.user.UserRepositoryImpl
import com.lambao.domain.service.user.UserService
import com.lambao.domain.service.user.UserServiceImpl
import com.lambao.routes.configureAuth
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    DatabaseFactory.init()

    install(ContentNegotiation) {
        jackson()
    }

    val repository: UserRepository = UserRepositoryImpl()
    val service: UserService = UserServiceImpl(repository)
    configureAuth(service)
}
