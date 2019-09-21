package com.mctech.features.login

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mctech.domain.errors.AuthException
import com.mctech.domain.interaction.Result
import com.mctech.domain.interaction.auth.AuthenticationUseCase
import com.mctech.domain.interaction.auth.RegisterUserUseCase
import com.mctech.domain.model.AuthRequest
import com.mctech.domain.model.RegisterUser
import com.mctech.feature.arq.BaseViewModel
import com.mctech.features.login.state.LoginState

class LoginViewModel(
    private val authenticationUseCase: AuthenticationUseCase,
    private val registerUserUseCase: RegisterUserUseCase
) : BaseViewModel() {

    private val _loginSreenState = MutableLiveData<LoginState>(LoginState.Unauthenticated)
    val loginSreenState: LiveData<LoginState> get() = _loginSreenState

    private val _lastAuthRequest = MutableLiveData<AuthRequest>()
    val lastAuthRequest: LiveData<AuthRequest> get() = _lastAuthRequest

    fun navigationToSignUp(authRequest: AuthRequest) {
        _lastAuthRequest.value = authRequest
    }

    @MainThread
    suspend fun doLogin(authRequest: AuthRequest) {
        _loginSreenState.value = LoginState.Loading

        val authResult = authenticationUseCase.execute(authRequest)

        when (authResult) {
            is Result.Success<*> -> _loginSreenState.value = LoginState.Authenticated
            is Result.Failure -> _loginSreenState.value = LoginState.Error(
                error = authResult.throwable as AuthException
            )
        }
    }

    @MainThread
    suspend fun registerUser(registerUser: RegisterUser){
        _loginSreenState.value = LoginState.Loading

    }
}
