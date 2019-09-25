package com.mctech.domain.interaction.auth

import com.mctech.domain.errors.AuthException
import com.mctech.domain.interaction.Result
import com.mctech.domain.model.RegisterUser
import com.mctech.domain.model.User
import com.mctech.domain.services.AuthService

class RegisterUserUseCase(private val authService: AuthService) {
    suspend fun execute(registerUser: RegisterUser): Result<User> {
        try {
            registerUser.validateOrThrow()

            // Register user
            if (authService.registerUser(registerUser)) {
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
