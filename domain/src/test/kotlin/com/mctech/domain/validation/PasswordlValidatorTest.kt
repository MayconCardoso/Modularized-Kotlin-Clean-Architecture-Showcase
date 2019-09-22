package com.mctech.domain.validation

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * @author MAYCON CARDOSO on 2019-09-21.
 */
class PasswordlValidatorTest {

    @Test
    fun `should validate`() {
        assertTrue(PasswordlValidator("dsa@asio@90190"))
        assertTrue(PasswordlValidator("12345"))
        assertTrue(PasswordlValidator("123456"))
    }

    @Test
    fun `should fail when password under 5 characters`() {
        assertFalse(PasswordlValidator("1"))
        assertFalse(PasswordlValidator("12"))
        assertFalse(PasswordlValidator("123"))
        assertFalse(PasswordlValidator("1234"))
    }
}