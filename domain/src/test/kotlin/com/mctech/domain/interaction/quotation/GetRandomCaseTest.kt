package com.mctech.domain.interaction.quotation

import com.mctech.domain.TestDataFactory
import com.mctech.domain.errors.QuotationException
import com.mctech.domain.interaction.Result
import com.mctech.domain.services.QuotationService
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

/**
 * @author MAYCON CARDOSO on 2019-09-30.
 */
@ExperimentalCoroutinesApi
class GetRandomCaseTest {
    private val service = mock<QuotationService>()
    private lateinit var getRandomCase: GetRandomCase

    @Before
    fun `before each test`() {
        getRandomCase = GetRandomCase(service)
    }

    @Test
    fun `should return quotation`() = runBlockingTest {
        val expectedValue = TestDataFactory.createQuotation()

        whenever(service.getRandom()).thenReturn(expectedValue)

        val result = getRandomCase.execute()

        assertThat(result)
            .isExactlyInstanceOf(Result.Success::class.java)
            .isEqualTo(Result.Success(expectedValue))
    }

    @Test
    fun `should return known exception`() = failureAssertion(
        exception = QuotationException.ConnectionIssueException,
        expectedException = QuotationException.ConnectionIssueException
    )

    @Test
    fun `should return unknown exception`() = failureAssertion(
        exception = RuntimeException(),
        expectedException = QuotationException.UnknownQuotationException
    )

    private fun failureAssertion(exception: Throwable, expectedException: Exception) =
        runBlockingTest {
            whenever(service.getRandom()).thenThrow(exception)

            val result = getRandomCase.execute()
            val resultException = (result as Result.Failure).throwable

            assertThat(result).isInstanceOf(Result.Failure::class.java)
            assertThat(resultException).isEqualTo(expectedException)
        }
}