package com.mctech.data.quotation.datasource

import com.mctech.domain.model.Quotation

interface QuotationCacheDataSource {
    suspend fun saveByTag(tag: String, page: Int?)
    suspend fun getRandom(): Quotation?
    suspend fun getByTag(tag: String, page: Int?): List<Quotation>?
}