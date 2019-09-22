package com.mctech.domain.model

import com.mctech.domain.errors.AuthException
import org.assertj.core.api.Assertions
import org.junit.Test

/**
 * @author MAYCON CARDOSO on 2019-09-21.
 */
class RegisterUserTest{
    companion object{
        const val EMAIL = "maycon.santos.cardoso@gmail.com"
        const val NAME = "Maycon dos Santos Cardoso"
        const val PASSWORD = "123456789"
    }

    @Test
    fun `should throw when name is empty`() {
        val request = createAuthRequest(
            email = EMAIL,
            password = PASSWORD
        )

        Assertions.assertThatThrownBy { request.validateOrThrow() }
            .isEqualTo(
                AuthException.EmptyFormValueException
            )
    }

    @Test
    fun `should throw when email fail`() {
        val request = createAuthRequest(
            name = NAME,
            password = PASSWORD
        )

        Assertions.assertThatThrownBy { request.validateOrThrow() }
            .isEqualTo(
                AuthException.InvalidEmailFormatException
            )
    }

    @Test
    fun `should throw when password fail`() {
        val request = createAuthRequest(
            name = NAME,
            email = EMAIL
        )

        Assertions.assertThatThrownBy { request.validateOrThrow() }
            .isEqualTo(
                AuthException.PasswordUnderFiveCharactersException
            )
    }

    @Test
    fun `should validate`() {
        val request = createAuthRequest(
            email = EMAIL,
            name = NAME,
            password = PASSWORD
        )
        request.validateOrThrow()
    }

    private fun createAuthRequest(email: String = "", name : String = "", password : String = "") = RegisterUser(
        user = User(
            id = "",
            name = name,
            email = email,
            profilePicture = ""
        ),
        password = password
    )
}