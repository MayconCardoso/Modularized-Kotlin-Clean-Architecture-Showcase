package com.mctech.data.quotation.datasource

import com.mctech.data.quotation.api.QuotationAPI
import com.mctech.domain.model.Quotation

/**
 * @author MAYCON CARDOSO on 2019-09-30.
 */
class QuotationRemoteDataSourceImpl(private val api: QuotationAPI) :
    QuotationDataSource {
    override suspend fun getRandom() = api.getRandom().let {
        Quotation(
            it.id,
            it.description,
            it.date,
            it.tags,
            "",
            ""
        )
    }

    override suspend fun getByTag(tag: String, page: Int?): List<Quotation> {
        TODO()
    }
}