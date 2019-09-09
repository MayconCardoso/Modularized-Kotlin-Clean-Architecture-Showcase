package com.mctech.data.di

import com.google.firebase.auth.FirebaseAuth
import com.mctech.data.auth.AuthRepository
import com.mctech.domain.services.AuthService
import org.koin.dsl.module.module

val dataModule = module {
    // Auth
    single<FirebaseAuth> { FirebaseAuth.getInstance() }
    single<AuthService> {
        AuthRepository(get())
    }
}