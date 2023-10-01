package com.lambao.domain.usecase

import com.lambao.data.dto.RegisterUserBody
import com.lambao.domain.exceptions.ValidateException
import com.lambao.extensions.isNumber

class ValidateRegisterUseCase {

    operator fun invoke(body: RegisterUserBody) {
        if (body.phoneNumber.isEmpty()) {
            throw ValidateException("Phone number is not empty!")
        }

        if (!body.phoneNumber.isNumber()) {
            throw ValidateException("Phone number is not contain text or character!")
        }

        if (body.phoneNumber.length in 0..3) {
            throw ValidateException("Phone number length is 10-11")
        }

        if (body.password.length in 0..3) {
            throw ValidateException("Password length must be more than 3 character")
        }
    }
}