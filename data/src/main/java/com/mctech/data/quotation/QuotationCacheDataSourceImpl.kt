package com.mctech.data.quotation

import com.mctech.domain.model.Quotation

/**
 * @author MAYCON CARDOSO on 2019-09-30.
 */
class QuotationCacheDataSourceImpl : QuotationCacheDataSource {
    override fun saveByTag(tag: String, page: Int?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getRandom(): Quotation? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getByTag(tag: String, page: Int?): List<Quotation>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}