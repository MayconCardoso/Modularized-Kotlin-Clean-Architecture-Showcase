package com.mctech.features.login.interaction

import com.mctech.domain.model.AuthRequest
import com.mctech.domain.model.RegisterUser
import com.mctech.feature.arq.UserInteraction

sealed class LoginUserInteraction : UserInteraction {
    data class NavigateToSignUn(val authRequest: AuthRequest) : LoginUserInteraction()
    data class TryLogin(val authRequest: AuthRequest) : LoginUserInteraction()
    data class TryRegisterUser(val registerUser: RegisterUser) : LoginUserInteraction()
}