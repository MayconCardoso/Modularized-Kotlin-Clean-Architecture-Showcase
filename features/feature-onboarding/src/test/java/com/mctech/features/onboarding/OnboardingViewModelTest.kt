package com.mctech.features.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.mctech.domain.interaction.Result
import com.mctech.domain.interaction.auth.CheckAuthSessionUseCase
import com.mctech.feature.arq.ComponentState
import com.mctech.features.onboarding.state.OnBoardingNavigationState
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.AutoCloseKoinTest

/**
 * @author MAYCON CARDOSO on 2019-09-22.
 */
@ExperimentalCoroutinesApi
class OnboardingViewModelTest : AutoCloseKoinTest() {
    @get:Rule val koinRule              = KoinModuleTestRule()
    @get:Rule val coroutinesTestRule    = CoroutinesMainTestRule()

    private val checkAuthSessionUseCase = mock<CheckAuthSessionUseCase>()
    private lateinit var onboardingViewModel: OnboardingViewModel

    @Before
    fun `before each test`() {
        onboardingViewModel = OnboardingViewModel(checkAuthSessionUseCase)
    }

    @Test
    fun `should emit authorized state`()  = onBoardTestStateHandler(
        isLogged = true,
        expectedState = OnBoardingNavigationState.Authorized
    )

        @Test
    fun `should emit unauthorized state`() = onBoardTestStateHandler(
        isLogged = false,
        expectedState = OnBoardingNavigationState.Unauthorized
    )

    private fun onBoardTestStateHandler(
        isLogged : Boolean,
        expectedState : OnBoardingNavigationState
    ) = runBlocking {
        whenever(checkAuthSessionUseCase.execute()).thenReturn(Result.Success(isLogged))
        onboardingViewModel.userFlowState.collectValuesWhenCoroutineBuilder{
            assertThat(it.size).isEqualTo(2)
            assertThat(it[0]).isEqualTo(ComponentState.Loading)
            assertThat(it[1]).isInstanceOf(ComponentState.Success::class.java)

            val result = (it[1] as ComponentState.Success<OnBoardingNavigationState>).result
            assertThat(result).isEqualTo(expectedState)
        }
    }
}

fun <T> LiveData<T>.collectValuesWhenCoroutineBuilder(block : (List<T>) -> Unit) {
    val emittedValues = mutableListOf<T>()
    val observer = Observer<T> {
        emittedValues.add(it)
    }

    try {
        observeForever(observer)
        block(emittedValues)
    } finally {
        removeObserver(observer)
    }
}