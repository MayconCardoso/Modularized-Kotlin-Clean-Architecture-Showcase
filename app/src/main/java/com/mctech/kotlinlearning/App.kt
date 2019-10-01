package com.mctech.kotlinlearning

import androidx.multidex.MultiDexApplication
import com.mctech.data.di.dataModule
import com.mctech.features.login.di.loginModule
import com.mctech.features.onboarding.di.onboardingModule
import com.mctech.kotlinlearning.di.modules.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : MultiDexApplication(){
    override fun onCreate() {
        super.onCreate()
        initDependencyInjection()
    }

    private fun initDependencyInjection() {
        startKoin{
            androidLogger()
            androidContext(this@App)
            modules(listOf(
                // Platform
                dataModule,
                useCaseModules,

                // Libraries
                loggingModule,
                analyticsModule,
                networkingModule,

                // Features
                navigatorModule,
                onboardingModule,
                loginModule
            ))
        }
    }
}
