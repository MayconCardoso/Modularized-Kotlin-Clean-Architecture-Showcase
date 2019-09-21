package com.mctech.features.onboarding.state

sealed class OnBoardingNavigationState {
    object Authorized : OnBoardingNavigationState()
    object Unauthorized : OnBoardingNavigationState()
}