package com.mctech.features.onboarding.state

sealed class OnBoardingNavigationState {
    object Login : OnBoardingNavigationState()
    object Dashboard : OnBoardingNavigationState()
}