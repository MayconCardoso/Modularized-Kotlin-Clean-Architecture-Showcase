package com.mctech.feature.random_joke

import com.mctech.domain.errors.QuotationException
import com.mctech.domain.interaction.Result
import com.mctech.domain.interaction.quotation.GetRandomQuotationCase
import com.mctech.domain.model.Quotation
import com.mctech.feature.arq.ComponentState
import com.mctech.feature.random_joke.interaction.RandomQuotationInteraction
import com.mctech.test.arq.BaseViewModelTest
import com.mctech.test.arq.extentions.*
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test
import java.util.*


/**
 * @author MAYCON CARDOSO on 2019-11-20.
 */
class RandomQuotationViewModelTest : BaseViewModelTest() {
    private lateinit var viewModel: RandomQuotationViewModel

    private val expectationQuotation = createQuotation()
    private val getRandomQuotationCase = mock<GetRandomQuotationCase>()



    @Before
    fun `before each test`() {
        viewModel = RandomQuotationViewModel(
            getRandomQuotationCase
        )
    }

    @Test
    fun `should init component`() {
        viewModel.quotationState.collectValuesForTesting {
            it.assertCount(1)
            it.assertFirst().isEqualTo(
                ComponentState.Initializing
            )
        }
    }

    @Test
    fun `should display quotation`() {
        viewModel.quotationState.test(
            scenario = {
                whenever(getRandomQuotationCase.execute()).thenReturn(Result.Success(expectationQuotation))
            },
            action = {
                viewModel.interact(RandomQuotationInteraction.LoadRandomQuotation)
            },
            assertion = {
                val successValue = it[2] as ComponentState.Success<Quotation>

                it.assertCount(3)
                it.assertFirst().isEqualTo(ComponentState.Initializing)
                it.assertAtPosition(2).isExactlyInstanceOf(ComponentState.Success::class.java)

                Assertions.assertThat(successValue.result).isEqualTo(expectationQuotation)
            }
        )
    }

    @Test
    fun `should display error`() {
        viewModel.quotationState.test(
            scenario = {
                whenever(getRandomQuotationCase.execute()).thenReturn(
                    Result.Failure(
                        QuotationException.UnknownQuotationException
                    )
                )
            },
            action = {
                viewModel.interact(RandomQuotationInteraction.LoadRandomQuotation)
            },
            assertion = {
                val successValue = it[2] as ComponentState.Error

                it.assertCount(3)
                it.assertFirst().isEqualTo(ComponentState.Initializing)
                it.assertAtPosition(2).isExactlyInstanceOf(ComponentState.Error::class.java)

                Assertions.assertThat(successValue.reason).isEqualTo(
                    QuotationException.UnknownQuotationException
                )
            }
        )
    }

    private fun createQuotation(
        id : String = "",
        tag : String = "",
        description: String = "",
        date : Date = Date(),
        author : String = "",
        twitterLink : String = ""
    ) = Quotation(
        id = id,
        description = description,
        date = date,
        author = author,
        twitterLink = twitterLink,
        tag = listOf(tag)
    )
}