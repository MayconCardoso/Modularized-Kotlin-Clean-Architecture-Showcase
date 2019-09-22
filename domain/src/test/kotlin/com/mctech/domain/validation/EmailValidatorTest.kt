package com.mctech.domain.validation

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * @author MAYCON CARDOSO on 2019-09-21.
 */
class EmailValidatorTest {

    @Test
    fun `should validate`() {
        assertTrue(EmailValidator("maycon.santos.cardoso@gmail.com"))
        assertTrue(EmailValidator("maycon.santos.cardoso@gmail.com.br"))
        assertTrue(EmailValidator("maycon.1994_teste@gmail.com.br"))
    }

    @Test
    fun `should fail when email empty`() {
        assertFalse(EmailValidator(""))
    }

    @Test
    fun `should fail when email null`() {
        assertFalse(EmailValidator(null))
    }

    @Test
    fun `should fail when email without at`() {
        assertFalse(EmailValidator("teste.teste.com.br"))
        assertFalse(EmailValidator("testetesteteste"))
    }

    @Test
    fun `should fail when email double at`() {
        assertFalse(EmailValidator("teste@@teste.com"))
        assertFalse(EmailValidator("teste@teste@teste.com"))
    }

    @Test
    fun `should fail when email double dot`() {
        assertFalse(EmailValidator("teste@teste..com"))
    }
    @Test
    fun `should fail when email dot in the end`() {
        assertFalse(EmailValidator("teste@teste.com."))
    }
}