package com.mctech.domain.interaction.auth

import com.mctech.domain.errors.AuthException
import com.mctech.domain.interaction.Result
import com.mctech.domain.model.AuthRequest
import com.mctech.domain.model.User
import com.mctech.domain.services.AuthService
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

/**
 * @author MAYCON CARDOSO on 2019-09-22.
 */
@ExperimentalCoroutinesApi
class AuthenticationUseCaseTest {
    private val authService = mock<AuthService>()
    private val authRequest = mock<AuthRequest>()
    private val expectedUser = User(name = "", email = "")

    private lateinit var authSessionUseCase: AuthenticationUseCase

    @Before
    fun `before each test`() {
        authSessionUseCase = AuthenticationUseCase(authService)
    }

    @Test
    fun `should call request validation`() = runBlockingTest {
        authSessionUseCase.execute(authRequest)
        verify(authRequest).validateOrThrow()
    }

    @Test
    fun `should sign in`() = runBlockingTest {
        whenever(authService.login(authRequest)).thenReturn(true)
        whenever(authService.fetchLoggedUser()).thenReturn(expectedUser)

        val result = authSessionUseCase.execute(authRequest)
        val resultUser = (result as Result.Success<User>).result

        assertThat(result).isInstanceOf(Result.Success::class.java)
        assertThat(resultUser).isEqualTo(expectedUser)
    }

    @Test
    fun `should fail throwing auth exception`() = runBlockingTest {
        whenever(authService.login(any())).thenThrow(AuthException.WrongCredentialsException)

        val result = authSessionUseCase.execute(authRequest)
        val resultException = (result as Result.Failure).throwable

        assertThat(result).isInstanceOf(Result.Failure::class.java)
        assertThat(resultException).isEqualTo(AuthException.WrongCredentialsException)
    }

    @Test
    fun `should fail with unknown exception when any unknown problem happen`() = runBlockingTest {
        whenever(authService.login(any())).thenReturn(false)
        whenever(authService.fetchLoggedUser()).thenReturn(expectedUser)

        val result = authSessionUseCase.execute(authRequest)
        val resultException = (result as Result.Failure).throwable

        assertThat(result).isInstanceOf(Result.Failure::class.java)
        assertThat(resultException).isEqualTo(AuthException.UnknownAuthException)
    }

    @Test
    fun `should fail mapping unknown exception`() = runBlockingTest {
        whenever(authService.login(any())).thenThrow(IllegalArgumentException())

        val result = authSessionUseCase.execute(authRequest)
        val resultException = (result as Result.Failure).throwable

        assertThat(result).isInstanceOf(Result.Failure::class.java)
        assertThat(resultException).isEqualTo(AuthException.UnknownAuthException)
    }
}