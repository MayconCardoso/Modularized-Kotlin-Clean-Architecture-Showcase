package com.mctech.kotlinlearning.di.modules

import com.mctech.libraries.analytics.AnalyticsHelper
import com.mctech.libraries.analytics.FirebaseAnalyticsHelper
import org.koin.dsl.module.module

/**
 * @author MAYCON CARDOSO on 2019-09-05.
 */
val analyticsModule = module {
    single<AnalyticsHelper> {
        FirebaseAnalyticsHelper(
            get()
        )
    }
}