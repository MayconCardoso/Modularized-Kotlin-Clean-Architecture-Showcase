package com.mctech.domain.model

import com.mctech.domain.errors.AuthException
import com.mctech.domain.validation.EmailValidator

/**
 * @author MAYCON CARDOSO on 2019-07-25.
 */
data class AuthRequest(
    val type: AuthRequestType = AuthRequestType.EMAIL,
    val email: String,
    var password: String
) {
    fun validateOrThrow() {
        if (!EmailValidator(email))
            throw AuthException.InvalidEmailFormatException
    }
}

enum class AuthRequestType {
    EMAIL
}

