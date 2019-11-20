package com.mctech.data.quotation.repository

import com.mctech.data.quotation.datasource.QuotationCacheDataSource
import com.mctech.data.quotation.datasource.QuotationDataSource
import com.mctech.domain.errors.QuotationException
import com.mctech.domain.model.Quotation
import com.mctech.domain.services.QuotationService
import com.mctech.library.networking.NetworkError

/**
 * @author MAYCON CARDOSO on 2019-09-30.
 */
class QuotationRepository(
    private val cacheDataSource: QuotationCacheDataSource,
    private val remoteDataSource: QuotationDataSource
) : QuotationService {

    override suspend fun getRandom(): Quotation {
        return try {
            remoteDataSource.getRandom()
        } catch (exception: Exception) {
            throw QuotationException.UnknownQuotationException
        }
    }

    override suspend fun getByTag(tag: String, page: Int?): List<Quotation> {
        return try {
            remoteDataSource.getByTag(tag, page).apply {
                cacheDataSource.saveByTag(tag, page)
            }
        } catch (exception: Exception) {
            if (exception is NetworkError) {
                return cacheDataSource.getByTag(tag, page)
                    ?: throw QuotationException.UnknownQuotationException
            }
            throw QuotationException.UnknownQuotationException
        }
    }
}