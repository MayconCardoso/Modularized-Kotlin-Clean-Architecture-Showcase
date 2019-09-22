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
            password = PASSWORD,
            passwordConfirmation = PASSWORD
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
            password = PASSWORD,
            passwordConfirmation = PASSWORD
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
    fun `should throw when passwords do not match`() {
        val request = createAuthRequest(
            name = NAME,
            password = "123456",
            passwordConfirmation = "654321",
            email = EMAIL
        )

        Assertions.assertThatThrownBy { request.validateOrThrow() }
            .isEqualTo(
                AuthException.PasswordsDoNotMatchException
            )
    }

    @Test
    fun `should validate`() {
        val request = createAuthRequest(
            email = EMAIL,
            name = NAME,
            password = PASSWORD,
            passwordConfirmation = PASSWORD
        )
        request.validateOrThrow()
    }

    private fun createAuthRequest(
        email: String = "",
        name : String = "",
        password : String = "",
        passwordConfirmation : String = ""
    ) = RegisterUser(
        user = User(
            name = name,
            email = email
        ),
        password = password,
        passwordConfirmation = passwordConfirmation
    )
}