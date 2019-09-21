package com.mctech.domain.interaction.auth

import com.mctech.domain.errors.AuthException
import com.mctech.domain.interaction.Result
import com.mctech.domain.model.RegisterUser
import com.mctech.domain.services.AuthService
import com.mctech.domain.validation.EmailValidator
import com.mctech.domain.validation.PasswordlValidator
import com.mctech.domain.validation.PhoneNumberValidator

class RegisterUserUseCase(private val authService: AuthService) {
    suspend fun execute(registerUser: RegisterUser): Result {
        try {
            // Validation
            with(registerUser){
                user.email.takeUnless {
                    EmailValidator(it)
                }?.let {
                    throw AuthException.InvalidEmailFormatException
                }

                user.phoneNumber.takeUnless {
                    PhoneNumberValidator(it)
                }?.let {
                    throw AuthException.InvalidPhoneNumberFormatException
                }

                password.takeUnless {
                    PasswordlValidator(it)
                }?.let {
                    throw AuthException.PasswordUnderFiveCharactersException
                }
            }

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
