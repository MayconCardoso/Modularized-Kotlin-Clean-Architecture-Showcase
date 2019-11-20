package com.mctech.feature.random_joke.di

import com.mctech.feature.random_joke.RandomQuotationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author MAYCON CARDOSO on 2019-09-30.
 */
val randomQuotationModel = module {
    viewModel {
        RandomQuotationViewModel(
            getRandomCase = get()
        )
    }
}