package com.mctech.kotlinlearning

import androidx.multidex.MultiDexApplication
import com.mctech.data.di.dataModule
import com.mctech.kotlinlearning.di.modules.analyticsModule
import com.mctech.kotlinlearning.di.modules.loggingModule
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
                // Plataform
                dataModule,

                // Libraries
                loggingModule,
                analyticsModule
            ))
        }
    }
}
