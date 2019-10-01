package com.mctech.data.quotation

import com.mctech.domain.errors.QuotationException
import com.mctech.domain.model.Quotation
import com.mctech.domain.services.QuotationService

/**
 * @author MAYCON CARDOSO on 2019-09-30.
 */
class QuotationRepository(
    private val cacheDataSource: QuotationCacheDataSourceImpl,
    private val remoteDataSource: QuotationRemoteDataSourceImpl
) : QuotationService {
    override suspend fun getRandom(): Quotation {
        return try {
            remoteDataSource.getRandom()
        } catch (exception: Exception){
            throw QuotationException.UnknownQuotationException
        }
    }

    override suspend fun getByTag(tag: String, page: Int?): List<Quotation> {
        return try {
            cacheDataSource.getByTag(tag, page)
                ?: remoteDataSource.getByTag(tag, page).apply {
                    cacheDataSource.saveByTag(tag, page)
                }
        } catch (exception: Exception){
            throw QuotationException.UnknownQuotationException
        }
    }
}