package com.mctech.features.onboarding

sealed class OnBoardingNavigationState {
    object Login : OnBoardingNavigationState()
    object Dashboard : OnBoardingNavigationState()
}