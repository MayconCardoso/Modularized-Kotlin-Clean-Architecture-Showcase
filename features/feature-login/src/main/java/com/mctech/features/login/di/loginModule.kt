package com.mctech.features.login.di

import com.mctech.features.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    viewModel {
        LoginViewModel(
            get(),
            get()
        )
    }
}