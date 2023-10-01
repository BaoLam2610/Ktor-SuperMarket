package com.lambao.plugins

import com.lambao.data.repository.user.UserRepository
import com.lambao.data.repository.user.UserRepositoryImpl
import com.lambao.domain.service.user.UserService
import com.lambao.domain.service.user.UserServiceImpl
import com.lambao.domain.usecase.ValidateLoginUseCase
import com.lambao.domain.usecase.ValidateRegisterUseCase
import io.ktor.server.application.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger()
        modules(appModule)
    }
}

val appModule = module {
    single<UserRepository> { UserRepositoryImpl() }
    single { ValidateLoginUseCase() }
    single { ValidateRegisterUseCase() }
    single<UserService> { UserServiceImpl(get(), get(), get()) }
}