package com.mctech.kotlinlearning.di.modules

import androidx.fragment.app.FragmentActivity
import com.mctech.features.login.LoginActivity
import com.mctech.features.navigation.Navigator
import com.mctech.features.navigation.Screen
import org.koin.dsl.module


val navigatorModule = module {
    single {
        mapOf<Screen, Class<out FragmentActivity>>(
            Pair(Screen.Login, LoginActivity::class.java)
        )
    }

    factory { (view: FragmentActivity) -> Navigator(view, get()) }
}