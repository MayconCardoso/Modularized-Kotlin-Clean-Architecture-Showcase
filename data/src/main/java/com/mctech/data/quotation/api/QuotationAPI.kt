package com.mctech.data.quotation.api

import com.mctech.data.BuildConfig
import com.mctech.data.quotation.model.RandomQuotationResponse
import retrofit2.http.GET
import retrofit2.http.Headers

/**
 * @author MAYCON CARDOSO on 2019-11-06.
 */
interface QuotationAPI {
    companion object {
        const val API_KEY = BuildConfig.ApiKey
    }

    @Headers(
        "x-rapidapi-host:matchilling-tronald-dump-v1.p.rapidapi.com",
        "accept:application/hal+json",
        "x-rapidapi-key:$API_KEY"
    )
    @GET("random/quote")
    suspend fun getRandom(): RandomQuotationResponse
}