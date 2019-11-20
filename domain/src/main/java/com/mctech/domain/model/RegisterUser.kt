package com.mctech.domain.model

import com.mctech.domain.errors.AuthException
import com.mctech.domain.validation.EmailValidator
import com.mctech.domain.validation.PasswordlValidator

/**
 * @author MAYCON CARDOSO on 2019-07-25.
 */
data class RegisterUser(
    val user: User,
    val password: String,
    val passwordConfirmation : String
) {
    fun validateOrThrow() {
        if (user.name.isEmpty())
            throw AuthException.EmptyFormValueException

        if (!EmailValidator(user.email))
            throw AuthException.InvalidEmailFormatException

        if (!PasswordlValidator(password))
            throw AuthException.PasswordUnderSixCharactersException

        if (password != passwordConfirmation)
            throw AuthException.PasswordsDoNotMatchException
    }
}