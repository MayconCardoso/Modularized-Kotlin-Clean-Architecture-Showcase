package com.mctech.features.onboarding

import android.os.Bundle
import com.mctech.feature.arq.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author MAYCON CARDOSO on 2019-07-23.
 */
class OnboardingActivity : BaseActivity<OnboardingViewModel>() {
    private val viewModelAgent: OnboardingViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        // Start the application.
        viewModelAgent.startApplication()
    }
}