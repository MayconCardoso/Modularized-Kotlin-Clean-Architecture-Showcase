package com.mctech.data.di

import com.google.firebase.auth.FirebaseAuth
import com.mctech.data.auth.AuthRepository
import com.mctech.data.quotation.api.QuotationAPI
import com.mctech.data.quotation.datasource.QuotationCacheDataSource
import com.mctech.data.quotation.datasource.QuotationCacheDataSourceImpl
import com.mctech.data.quotation.datasource.QuotationDataSource
import com.mctech.data.quotation.datasource.QuotationRemoteDataSourceImpl
import com.mctech.data.quotation.repository.QuotationRepository
import com.mctech.domain.services.AuthService
import com.mctech.domain.services.QuotationService
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {
    // Auth
    single<FirebaseAuth> { FirebaseAuth.getInstance() }
    single<AuthService> {
        AuthRepository(
            firebaseAuth = get()
        )
    }

    // Quotation
    single<QuotationCacheDataSource> { QuotationCacheDataSourceImpl() }
    single<QuotationDataSource> {
        val retrofit = get<Retrofit>()
        val api = retrofit.create(QuotationAPI::class.java)

        QuotationRemoteDataSourceImpl(api)
    }
    single<QuotationService> {
        QuotationRepository(
            cacheDataSource = get(),
            remoteDataSource = get()
        )
    }
}