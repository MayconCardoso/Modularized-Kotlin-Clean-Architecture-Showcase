package com.mctech.kotlinlearning.di.modules

import com.mctech.domain.interaction.auth.AuthenticationUseCase
import com.mctech.domain.interaction.auth.CheckAuthSessionUseCase
import com.mctech.domain.interaction.auth.RegisterUserUseCase
import com.mctech.domain.interaction.quotation.GetRandomQuotationCase
import org.koin.dsl.module

val useCaseModules = module {
    // Auth
    factory { CheckAuthSessionUseCase(get()) }
    factory { RegisterUserUseCase(get()) }
    factory { AuthenticationUseCase(get()) }

    // Quotation
    factory { GetRandomQuotationCase(get()) }
}