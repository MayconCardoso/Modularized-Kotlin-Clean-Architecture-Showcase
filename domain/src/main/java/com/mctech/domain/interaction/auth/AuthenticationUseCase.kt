package com.mctech.domain.interaction.auth

import com.mctech.domain.errors.AuthException
import com.mctech.domain.interaction.Result
import com.mctech.domain.model.AuthRequest
import com.mctech.domain.services.AuthService

class AuthenticationUseCase(private val authService: AuthService) {
    suspend fun execute(authRequest: AuthRequest): Result {
        try {
            authRequest.validateOrThrow()

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
