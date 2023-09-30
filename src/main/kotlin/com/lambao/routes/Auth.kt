package com.lambao.routes

import com.lambao.data.dto.UserDto
import com.lambao.domain.service.user.UserService
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureAuth(service: UserService) {
    routing {
        route("/auth") {
            post("/register") {
                val params = call.receive<UserDto>()
                val result = service.register(params)
                call.respond(result.statusCode, result)
            }
        }
    }
}