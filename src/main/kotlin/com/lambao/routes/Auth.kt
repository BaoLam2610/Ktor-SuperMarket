package com.lambao.routes

import com.lambao.data.dto.LoginUserBody
import com.lambao.data.dto.RegisterUserBody
import com.lambao.domain.service.user.UserService
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureAuth() {
    val userService by inject<UserService>()
    routing {
        route("/auth") {
            post("/register") {
                val params = call.receive<RegisterUserBody>()
                val result = userService.register(params)
                call.respond(result.statusCode, result)
            }

            post("/login") {
                val params = call.receive<LoginUserBody>()
                val result = userService.login(params)
                call.respond(result.statusCode, result)
            }
        }
    }
}