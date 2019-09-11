package com.mctech.domain.interaction.auth

import com.mctech.domain.errors.AuthException
import com.mctech.domain.interaction.Result
import com.mctech.domain.model.RegisterUser
import com.mctech.domain.model.checkEmail
import com.mctech.domain.model.checkPhoneNumber
import com.mctech.domain.services.AuthService

class RegisterUserUseCase(private val authService: AuthService) {
    suspend fun execute(registerUser: RegisterUser): Result {
        try{
            registerUser.user.checkEmail()
            registerUser.user.checkPhoneNumber()

            if(authService.registerUser(registerUser)){
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
