package com.mctech.domain.services

import com.mctech.domain.model.Quotation

/**
 * @author MAYCON CARDOSO on 2019-09-30.
 */
interface QuotationService {
    suspend fun getRandom(): Quotation
    suspend fun getByTag(tag: String, page : Int?): List<Quotation>
}