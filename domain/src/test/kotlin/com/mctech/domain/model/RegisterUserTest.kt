package com.mctech.domain.model

import com.mctech.domain.TestDataFactory
import com.mctech.domain.errors.AuthException
import org.assertj.core.api.Assertions.assertThatThrownBy
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
        val request = TestDataFactory.createRegisterUserRequest(
            email = EMAIL,
            password = PASSWORD,
            passwordConfirmation = PASSWORD
        )

        assertThatThrownBy { request.validateOrThrow() }
            .isEqualTo(
                AuthException.EmptyFormValueException
            )
    }

    @Test
    fun `should throw when email fail`() {
        val request = TestDataFactory.createRegisterUserRequest(
            name = NAME,
            password = PASSWORD,
            passwordConfirmation = PASSWORD
        )

        assertThatThrownBy { request.validateOrThrow() }
            .isEqualTo(
                AuthException.InvalidEmailFormatException
            )
    }

    @Test
    fun `should throw when password fail`() {
        val request = TestDataFactory.createRegisterUserRequest(
            name = NAME,
            email = EMAIL
        )

        assertThatThrownBy { request.validateOrThrow() }
            .isEqualTo(
                AuthException.PasswordUnderSixCharactersException
            )
    }

    @Test
    fun `should throw when passwords do not match`() {
        val request = TestDataFactory.createRegisterUserRequest(
            name = NAME,
            password = "123456",
            passwordConfirmation = "654321",
            email = EMAIL
        )

        assertThatThrownBy { request.validateOrThrow() }
            .isEqualTo(
                AuthException.PasswordsDoNotMatchException
            )
    }

    @Test
    fun `should validate`() {
        val request = TestDataFactory.createRegisterUserRequest(
            email = EMAIL,
            name = NAME,
            password = PASSWORD,
            passwordConfirmation = PASSWORD
        )
        request.validateOrThrow()
    }
}