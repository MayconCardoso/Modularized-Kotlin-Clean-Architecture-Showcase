package com.mctech.domain.interaction.auth

import com.mctech.domain.errors.AuthException
import com.mctech.domain.interaction.Result
import com.mctech.domain.model.AuthRequest
import com.mctech.domain.services.AuthService
import com.mctech.domain.validation.EmailValidator
import com.mctech.domain.validation.PasswordlValidator

class AuthenticationUseCase(private val authService: AuthService) {
    suspend fun execute(authRequest: AuthRequest): Result {
        try {
            // Validation
            with(authRequest) {
                email.takeUnless {
                    EmailValidator(it)
                }?.let {
                    throw AuthException.InvalidEmailFormatException
                }

                password.takeUnless {
                    PasswordlValidator(it)
                }?.let {
                    throw AuthException.PasswordUnderFiveCharactersException
                }
            }

            if (authService.login(authRequest)) {
                return Result.Success(authService.fetchLoggedUser()!!)
            }
        } catch (exception: Throwable) {
            return Result.Failure(
                if (exception is AuthException) exception
                else AuthException.UnknownAuthException
            )
        }

        return Result.Failure(AuthException.UnknownAuthException)
    }
}
