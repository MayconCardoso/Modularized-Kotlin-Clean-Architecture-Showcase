package com.mctech.domain.interaction.auth

import com.mctech.domain.interaction.Result
import com.mctech.domain.model.User
import com.mctech.domain.services.AuthService

class CheckAuthSessionUseCase(val authService: AuthService) {
    suspend fun execute(user: User) = Result.Success(authService.fetchLoggedUser() != null)
}
