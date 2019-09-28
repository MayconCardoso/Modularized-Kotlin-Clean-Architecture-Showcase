package com.mctech.features.login.state

import com.mctech.domain.errors.AuthException
import com.mctech.features.login.R

data class LoginErrorStateResources(val message: Int) {
    companion object {
        operator fun invoke(error: AuthException) =
            when (error) {
                is AuthException.EmptyFormValueException -> LoginErrorStateResources(
                    R.string.auth_form_empty
                )
                is AuthException.UserNotFoundException -> LoginErrorStateResources(
                    R.string.auth_user_not_found
                )
                is AuthException.InvalidEmailFormatException -> LoginErrorStateResources(
                    R.string.auth_email_bad_format
                )
                is AuthException.PasswordUnderFiveCharactersException -> LoginErrorStateResources(
                    R.string.auth_invalid_password
                )
                is AuthException.WrongCredentialsException -> LoginErrorStateResources(
                    R.string.auth_wrong_credentials
                )
                is AuthException.AlreadyRegisteredUserException -> LoginErrorStateResources(
                    R.string.auth_user_already_registered
                )
                is AuthException.PasswordsDoNotMatchException -> LoginErrorStateResources(
                    R.string.auth_password_dont_match
                )
                else -> LoginErrorStateResources(
                    R.string.auth_unknown_error
                )
            }
    }
}

fun LoginState.Error.toStateResource() = LoginErrorStateResources(error)