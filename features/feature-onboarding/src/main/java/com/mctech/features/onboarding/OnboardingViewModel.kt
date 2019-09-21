package com.mctech.features.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.mctech.domain.interaction.auth.CheckAuthSessionUseCase
import com.mctech.feature.arq.BaseViewModel
import com.mctech.feature.arq.ComponentState
import com.mctech.features.onboarding.state.OnBoardingNavigationState
import kotlinx.coroutines.delay

class OnboardingViewModel(private val checkAuthSessionUseCase: CheckAuthSessionUseCase) :
    BaseViewModel() {

    val userFlowState: LiveData<ComponentState<OnBoardingNavigationState>> = liveData {
        emit(ComponentState.Loading)
        delay(1000)

        val isUserLogged = checkAuthSessionUseCase.execute().result
        emit(
            ComponentState.Success(
                if (isUserLogged)
                    OnBoardingNavigationState.Authorized
                else
                    OnBoardingNavigationState.Unauthorized
            )
        )
    }
}