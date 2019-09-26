package com.mctech.features.onboarding

import com.mctech.domain.interaction.Result
import com.mctech.domain.interaction.auth.CheckAuthSessionUseCase
import com.mctech.feature.arq.ComponentState
import com.mctech.features.onboarding.state.OnBoardingNavigationState
import com.mctech.test.arq.BaseViewModelTest
import com.mctech.test.arq.extentions.assertCount
import com.mctech.test.arq.extentions.assertFirst
import com.mctech.test.arq.extentions.assertLast
import com.mctech.test.arq.extentions.collectValuesForTesting
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

/**
 * @author MAYCON CARDOSO on 2019-09-22.
 */
class OnboardingViewModelTest : BaseViewModelTest() {
    private val checkAuthSessionUseCase = mock<CheckAuthSessionUseCase>()
    private lateinit var onboardingViewModel: OnboardingViewModel

    @Before
    fun `before each test`() {
        onboardingViewModel = OnboardingViewModel(checkAuthSessionUseCase)
    }

    @Test
    fun `should emit authorized state`() = onBoardTestStateHandler(
        isLogged = true,
        expectedState = OnBoardingNavigationState.Authorized
    )

    @Test
    fun `should emit unauthorized state`() = onBoardTestStateHandler(
        isLogged = false,
        expectedState = OnBoardingNavigationState.Unauthorized
    )

    private fun onBoardTestStateHandler(
        isLogged: Boolean,
        expectedState: OnBoardingNavigationState
    ) {
        runBlocking {
            whenever(checkAuthSessionUseCase.execute()).thenReturn(Result.Success(isLogged))
            onboardingViewModel.userFlowState.collectValuesForTesting {
                it.assertCount(2)
                it.assertFirst().isEqualTo(ComponentState.Loading)
                it.assertLast().isInstanceOf(ComponentState.Success::class.java)

                val result = (it[1] as ComponentState.Success<OnBoardingNavigationState>).result
                assertThat(result).isEqualTo(expectedState)
            }
        }
    }
}