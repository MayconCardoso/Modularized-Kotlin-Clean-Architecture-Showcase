package com.mctech.domain.model

import com.mctech.domain.errors.AuthException
import org.assertj.core.api.Assertions
import org.junit.Test

/**
 * @author MAYCON CARDOSO on 2019-09-21.
 */
class AuthRequestTest {
    @Test
    fun `should throw when email fail`() {
        val request = createAuthRequest("")
        Assertions.assertThatThrownBy { request.validateOrThrow() }
            .isEqualTo(
                AuthException.InvalidEmailFormatException
            )
    }

    @Test
    fun `should validate`() {
        val request = createAuthRequest("maycon.santos.cardoso@gmail.com")
        request.validateOrThrow()
    }

    private fun createAuthRequest(email: String) = AuthRequest(
        email = email,
        password = ""
    )
}