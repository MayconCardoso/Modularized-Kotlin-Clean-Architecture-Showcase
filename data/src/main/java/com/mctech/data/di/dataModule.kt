package com.mctech.data.di

import com.google.firebase.auth.FirebaseAuth
import com.mctech.data.auth.AuthRepository
import org.koin.dsl.module.module

val dataModule = module {
    // Auth
    single { FirebaseAuth.getInstance() }
    single { AuthRepository(get()) }
}