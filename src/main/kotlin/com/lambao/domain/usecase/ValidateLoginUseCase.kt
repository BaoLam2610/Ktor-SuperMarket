package com.lambao.domain.usecase

import com.lambao.data.dto.LoginUserBody
import com.lambao.domain.exceptions.ValidateException
import com.lambao.extensions.isNumber

class ValidateLoginUseCase {

    operator fun invoke(body: LoginUserBody) {
        if (body.phone.isEmpty()) {
            throw ValidateException("Phone number is not empty!")
        }

        if (!body.phone.isNumber()) {
            throw ValidateException("Phone number is not contain text or character!")
        }

        if (body.phone.length in 0..3) {
            throw ValidateException("Phone number length is 10-11")
        }

        if (body.password.length in 0..3) {
            throw ValidateException("Password length must be more than 3 character")
        }
    }
}