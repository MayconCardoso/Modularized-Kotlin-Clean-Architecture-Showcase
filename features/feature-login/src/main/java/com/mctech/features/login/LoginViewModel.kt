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
import com.mctech.domain.model.User
import com.mctech.feature.arq.BaseViewModel
import com.mctech.feature.arq.UserInteraction
import com.mctech.features.login.interaction.LoginUserInteraction
import com.mctech.features.login.state.LoginState

class LoginViewModel(
    private val authenticationUseCase: AuthenticationUseCase,
    private val registerUserUseCase: RegisterUserUseCase
) : BaseViewModel() {
    private val _loginSreenState = MutableLiveData<LoginState>(LoginState.Unauthenticated)
    val loginSreenState: LiveData<LoginState> get() = _loginSreenState

    private val _lastAuthRequest = MutableLiveData<AuthRequest>()
    val lastAuthRequest: LiveData<AuthRequest> get() = _lastAuthRequest

    override suspend fun handleUserInteraction(interaction: UserInteraction) {
        when(interaction){
            is LoginUserInteraction.NavigateToSignUn    -> navigationToSignUp(interaction.authRequest)
            is LoginUserInteraction.TryLogin            -> doLogin(interaction.authRequest)
            is LoginUserInteraction.TryRegisterUser     -> registerUser(interaction.registerUser)
        }
    }


    @MainThread
    private suspend fun registerUser(registerUser: RegisterUser) = executeAuthInteraction {
        registerUserUseCase.execute(registerUser)
    }

    @MainThread
    private suspend fun doLogin(authRequest: AuthRequest) = executeAuthInteraction {
        authenticationUseCase.execute(authRequest)
    }

    @MainThread
    private fun navigationToSignUp(authRequest: AuthRequest) {
        _lastAuthRequest.value = authRequest
        _loginSreenState.value = LoginState.Unauthenticated
    }

    private suspend fun executeAuthInteraction(block: suspend () -> Result<User>) {
        _loginSreenState.value = LoginState.Loading

        when (val authResult = block.invoke()) {
            is Result.Success<*> -> _loginSreenState.value = LoginState.Authenticated
            is Result.Failure -> _loginSreenState.value = LoginState.Error(
                error = authResult.throwable as AuthException
            )
        }

    }
}
