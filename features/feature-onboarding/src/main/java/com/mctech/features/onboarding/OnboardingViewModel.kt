package com.mctech.features.onboarding

import com.mctech.domain.interaction.auth.CheckAuthSessionUseCase
import com.mctech.feature.arq.BaseViewModel

/**
 * @author MAYCON CARDOSO on 2019-09-05.
 */
class OnboardingViewModel(
    val checkAuthSessionUseCase: CheckAuthSessionUseCase
) : BaseViewModel(){

    fun startApplication(){
//        viewModelScope.launch {
//            // Coroutine that will be canceled when the ViewModel is cleared.
//        }
    }
}