package com.mctech.domain.interaction.auth

import com.mctech.domain.errors.AuthException
import com.mctech.domain.interaction.Result
import com.mctech.domain.model.RegisterUser
import com.mctech.domain.model.User
import com.mctech.domain.services.AuthService
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test

/**
 * @author MAYCON CARDOSO on 2019-09-24.
 */
class RegisterUserUseCaseTest {
    private val authService = mock<AuthService>()
    private val registerUserRequest = mock<RegisterUser>()
    private val expectedUser = User(name = "", email = "")

    private lateinit var useCase: RegisterUserUseCase

    @Before
    fun `before each test`() {
        useCase = RegisterUserUseCase(authService)
    }

    @Test
    fun `should call request validation`() = runBlockingTest {
        useCase.execute(registerUserRequest)
        verify(registerUserRequest).validateOrThrow()
    }

    @Test
    fun `should sign up`() = runBlockingTest {
        whenever(authService.registerUser(registerUserRequest)).thenReturn(true)
        whenever(authService.fetchLoggedUser()).thenReturn(expectedUser)

        val result = useCase.execute(registerUserRequest)
        val resultUser = (result as Result.Success<User>).result

        Assertions.assertThat(result).isInstanceOf(Result.Success::class.java)
        Assertions.assertThat(resultUser).isEqualTo(expectedUser)
    }


    @Test
    fun `should fail throwing auth exception`() = runBlockingTest {
        whenever(authService.registerUser(registerUserRequest)).thenThrow(AuthException.WrongCredentialsException)

        val result = useCase.execute(registerUserRequest)
        val resultException = (result as Result.Failure).throwable

        Assertions.assertThat(result).isInstanceOf(Result.Failure::class.java)
        Assertions.assertThat(resultException).isEqualTo(AuthException.WrongCredentialsException)
    }

    @Test
    fun `should fail with unknown exception when any unknown problem happen`() = runBlockingTest {
        whenever(authService.registerUser(registerUserRequest)).thenReturn(false)
        whenever(authService.fetchLoggedUser()).thenReturn(expectedUser)

        val result = useCase.execute(registerUserRequest)
        val resultException = (result as Result.Failure).throwable

        Assertions.assertThat(result).isInstanceOf(Result.Failure::class.java)
        Assertions.assertThat(resultException).isEqualTo(AuthException.UnknownAuthException)
    }

    @Test
    fun `should fail mapping unknown exception`() = runBlockingTest {
        whenever(authService.registerUser(registerUserRequest)).thenThrow(IllegalArgumentException())

        val result = useCase.execute(registerUserRequest)
        val resultException = (result as Result.Failure).throwable

        Assertions.assertThat(result).isInstanceOf(Result.Failure::class.java)
        Assertions.assertThat(resultException).isEqualTo(AuthException.UnknownAuthException)
    }

}