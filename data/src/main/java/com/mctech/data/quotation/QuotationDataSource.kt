package com.mctech.data.quotation

import com.mctech.domain.model.Quotation
import com.mctech.domain.services.QuotationService

/**
 * @author MAYCON CARDOSO on 2019-09-30.
 */
interface QuotationDataSource : QuotationService

interface QuotationCacheDataSource{
    fun saveByTag(tag : String, page : Int?)
    suspend fun getRandom(): Quotation?
    suspend fun getByTag(tag: String, page : Int?): List<Quotation>?
}