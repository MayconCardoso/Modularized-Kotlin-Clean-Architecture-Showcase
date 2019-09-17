package com.mctech.features.login.state

sealed class LoginScreenState {
    object Loading : LoginScreenState()
    object ShowingForm : LoginScreenState()
}