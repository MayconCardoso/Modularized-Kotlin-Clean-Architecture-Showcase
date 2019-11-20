package com.mctech.kotlinlearning.di.modules

import androidx.fragment.app.FragmentActivity
import com.mctech.feature.random_joke.RandomQuotationActivity
import com.mctech.features.login.LoginActivity
import com.mctech.features.navigation.Navigator
import com.mctech.features.navigation.Screen
import org.koin.dsl.module


val navigatorModule = module {
    single {
        mapOf<Screen, Class<out FragmentActivity>>(
            Screen.Login to LoginActivity::class.java,
            Screen.Dashboard to RandomQuotationActivity::class.java
        )
    }

    factory { (view: FragmentActivity) -> Navigator(view, get()) }
}