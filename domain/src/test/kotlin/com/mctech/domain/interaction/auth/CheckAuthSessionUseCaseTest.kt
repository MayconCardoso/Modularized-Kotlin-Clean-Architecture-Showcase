package com.mctech.domain.interaction.auth

import com.mctech.domain.interaction.Result
import com.mctech.domain.model.User
import com.mctech.domain.services.AuthService
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

/**
 * @author MAYCON CARDOSO on 2019-09-22.
 */
class CheckAuthSessionUseCaseTest {
    private val authService = mock<AuthService>()
    private lateinit var checkAuthSessionUseCase: CheckAuthSessionUseCase

    @Before
    fun `before each test`() {
        checkAuthSessionUseCase = CheckAuthSessionUseCase(authService)
    }

    @Test
    fun `should return true`() = runBlockingTest {
        whenever(authService.fetchLoggedUser()).thenReturn(
            User(
                name = "Teste",
                email = "Teste"
            )
        )
        performAssertion(
            result = checkAuthSessionUseCase.execute(),
            expectedValue = true
        )
    }

    @Test
    fun `should return false`() = runBlockingTest {
        whenever(authService.fetchLoggedUser()).thenReturn(null)
        performAssertion(
            result = checkAuthSessionUseCase.execute(),
            expectedValue = false
        )
    }

    private fun performAssertion(result: Result<Boolean>, expectedValue: Boolean) {
        assertThat(result)
            .isExactlyInstanceOf(Result.Success::class.java)
            .isEqualTo(Result.Success(expectedValue))
    }
}