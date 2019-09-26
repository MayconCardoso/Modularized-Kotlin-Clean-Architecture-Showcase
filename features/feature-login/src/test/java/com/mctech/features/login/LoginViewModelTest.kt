package com.mctech.features.login

import com.mctech.domain.errors.AuthException
import com.mctech.domain.interaction.Result
import com.mctech.domain.interaction.auth.AuthenticationUseCase
import com.mctech.domain.interaction.auth.RegisterUserUseCase
import com.mctech.domain.model.AuthRequest
import com.mctech.domain.model.RegisterUser
import com.mctech.domain.model.User
import com.mctech.features.login.interaction.LoginUserInteraction
import com.mctech.features.login.state.LoginState
import com.mctech.test.arq.BaseViewModelTest
import com.mctech.test.arq.extentions.*
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test


/**
 * @author MAYCON CARDOSO on 2019-09-25.
 */
class LoginViewModelTest : BaseViewModelTest() {
    private val authenticationUseCase = mock<AuthenticationUseCase>()
    private val registerUserUseCase = mock<RegisterUserUseCase>()

    private val authRequest = AuthRequest(email = "", password = "")
    private val expectedUser = User(name = "", email = "")
    private val registerRequest = RegisterUser(user = expectedUser, password = "", passwordConfirmation = "")
    private val expectedException = AuthException.WrongCredentialsException

    private lateinit var viewModel: LoginViewModel

    @Before
    fun `before each test`() {
        viewModel = LoginViewModel(
            authenticationUseCase,
            registerUserUseCase
        )
    }

    @Test
    fun `should init with unauthenticated state`() {
        viewModel.loginSreenState.collectValuesForTesting {
            it.assertCount(1)
            it.assertFirst().isEqualTo(
                LoginState.Unauthenticated
            )
        }
    }

    @Test
    fun `should change states when navigation to sign up screen`() {
        viewModel.lastAuthRequest.assertNoValue()

        viewModel.interact(LoginUserInteraction.NavigateToSignUn(authRequest))

        viewModel.loginSreenState.collectValuesForTesting {
            it.assertCount(1)
            it.assertFirst().isEqualTo(LoginState.Unauthenticated)
        }

        viewModel.lastAuthRequest.collectValuesForTesting {
            it.assertCount(1)
            it.assertFirst().isEqualTo(authRequest)
        }
    }

    @Test
    fun `should change state to authorized when sign in`() {
        assertPassOn(
            scenario = {
                authenticationUseCase.execute(authRequest)
            },
            interaction = LoginUserInteraction.TryLogin(authRequest)
        )
    }

    @Test
    fun `should change state to error when error while signing in`() {
        assertFailureOn(
            scenario = {
                authenticationUseCase.execute(authRequest)
            },
            interaction = LoginUserInteraction.TryLogin(authRequest)
        )
    }

    @Test
    fun `should change state to authorized when sign up`() {
        assertPassOn(
            scenario = {
                registerUserUseCase.execute(registerRequest)
            },
            interaction = LoginUserInteraction.TryRegisterUser(registerRequest)
        )
    }

    @Test
    fun `should change state to error when error while signing up`() {
        assertFailureOn(
            scenario = {
                registerUserUseCase.execute(registerRequest)
            },
            interaction = LoginUserInteraction.TryRegisterUser(registerRequest)
        )
    }

    private fun assertPassOn(interaction: LoginUserInteraction, scenario : suspend () -> Result<User>) {
        viewModel.loginSreenState.test(
            scenario = {
                whenever(scenario.invoke()).thenReturn(Result.Success(expectedUser))
            },
            action = {
                viewModel.interact(interaction)
            },
            assertion = {
                it.assertCount(3)
                it.assertAtPosition(0).isExactlyInstanceOf(LoginState.Unauthenticated::class.java)
                it.assertAtPosition(1).isExactlyInstanceOf(LoginState.Loading::class.java)
                it.assertAtPosition(2).isExactlyInstanceOf(LoginState.Authenticated::class.java)
            }
        )
    }

    private fun assertFailureOn(interaction: LoginUserInteraction, scenario : suspend () -> Result<User>) {
        viewModel.loginSreenState.test(
            scenario = {
                whenever(scenario.invoke()).thenReturn(Result.Failure(expectedException))
            },
            action = {
                viewModel.interact(interaction)
            },
            assertion = {
                it.assertCount(3)
                it.assertAtPosition(0).isExactlyInstanceOf(LoginState.Unauthenticated::class.java)
                it.assertAtPosition(1).isExactlyInstanceOf(LoginState.Loading::class.java)
                it.assertAtPosition(2).isExactlyInstanceOf(LoginState.Error::class.java)
                assertThat((it[2] as LoginState.Error).error).isEqualTo(expectedException)
            }
        )
    }
}