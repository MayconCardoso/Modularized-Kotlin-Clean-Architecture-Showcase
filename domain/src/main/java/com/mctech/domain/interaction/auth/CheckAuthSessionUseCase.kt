package com.mctech.domain.interaction.auth

import com.mctech.domain.interaction.Result
import com.mctech.domain.services.AuthService

class CheckAuthSessionUseCase(private val authService: AuthService) {
    suspend fun execute() = Result.Success(authService.fetchLoggedUser() != null)
}
