package com.mctech.features.onboarding

import android.os.Bundle
import com.mctech.feature.arq.BaseActivity
import com.mctech.feature.arq.ComponentState
import com.mctech.feature.arq.extentions.bindState
import com.mctech.features.navigation.Screen
import com.mctech.features.onboarding.state.OnBoardingNavigationState
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author MAYCON CARDOSO on 2019-07-23.
 */
class OnboardingActivity : BaseActivity<OnboardingViewModel>() {
    private val viewModelAgent: OnboardingViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        bindState(viewModelAgent.userFlowState){
            when(it){
                is ComponentState.Success -> navigate(it.result)
            }
        }
    }

    private fun navigate(result: OnBoardingNavigationState) {
        when(result){
            is OnBoardingNavigationState.Login -> navigator.navigateTo(Screen.Login)
        }
    }
}