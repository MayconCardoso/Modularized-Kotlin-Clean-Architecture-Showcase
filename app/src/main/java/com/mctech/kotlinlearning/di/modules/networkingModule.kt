package com.mctech.kotlinlearning.di.modules

import com.mctech.library.networking.RetrofitBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * @author MAYCON CARDOSO on 2019-09-05.
 */
val networkingModule = module {
    single {
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
    }

    single {
        RetrofitBuilder(
            apiURL = "https://matchilling-tronald-dump-v1.p.rapidapi.com/",
            httpClient = get()
        ) as Retrofit
    }
}