package com.mctech.features.login.state

import com.mctech.domain.errors.AuthException

sealed class LoginState {
    object Loading : LoginState()
    object Unauthenticated : LoginState()
    object Authenticated : LoginState()
    data class Error(val error : AuthException) : LoginState()
}