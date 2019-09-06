package com.mctech.domain.interaction.auth

import com.mctech.domain.errors.AuthException
import com.mctech.domain.interaction.Result
import com.mctech.domain.model.User
import com.mctech.domain.model.checkEmail
import com.mctech.domain.model.checkPhoneNumber
import com.mctech.domain.services.AuthService

class RegisterUserUseCase(val authService: AuthService) {
    suspend fun execute(user: User): Result {
        try{
            user.checkEmail()
            user.checkPhoneNumber()

            if(authService.registerUser(user)){
                return Result.Success(authService.fetchLoggedUser()!!)
            }
        }
        catch (exception : Throwable){
            return Result.Failure(
                if(exception is AuthException) exception
                else AuthException.UnknownAuthException
            )
        }

        return Result.Failure(AuthException.UnknownAuthException)
    }
}
