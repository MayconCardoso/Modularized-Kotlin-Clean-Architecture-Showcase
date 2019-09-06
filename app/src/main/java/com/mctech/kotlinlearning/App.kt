package com.mctech.kotlinlearning

import android.app.Application
import com.mctech.data.di.dataModule
import com.mctech.kotlinlearning.di.modules.analyticsModule
import com.mctech.kotlinlearning.di.modules.loggingModule
import org.koin.android.ext.android.startKoin

class App : Application(){
    override fun onCreate() {
        super.onCreate()

        initDependencyInjection()
    }

    private fun initDependencyInjection() {
        startKoin(
            androidContext = this,
            modules = listOf(
                loggingModule,
                analyticsModule,
                dataModule
            )
        )
    }
}
