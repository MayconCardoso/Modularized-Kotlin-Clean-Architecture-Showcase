package com.mctech.data.quotation.datasource

import com.mctech.domain.model.Quotation

/**
 * @author MAYCON CARDOSO on 2019-09-30.
 */
class QuotationCacheDataSourceImpl : QuotationCacheDataSource {
    override suspend fun saveByTag(tag: String, page: Int?) {
    }

    override suspend fun getRandom(): Quotation? = null

    override suspend fun getByTag(tag: String, page: Int?): List<Quotation>?  = null

}