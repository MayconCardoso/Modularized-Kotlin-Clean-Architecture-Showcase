package com.mctech.data.quotation.api

import com.mctech.data.quotation.model.RandomQuotationResponse
import retrofit2.http.GET

/**
 * @author MAYCON CARDOSO on 2019-11-06.
 */
interface QuotationAPI {
    @GET("random/quote")
    suspend fun getRandom() : RandomQuotationResponse
}