package com.mctech.domain.interaction.quotation

import com.mctech.domain.errors.QuotationException
import com.mctech.domain.interaction.Result
import com.mctech.domain.model.Quotation
import com.mctech.domain.services.QuotationService

/**
 * @author MAYCON CARDOSO on 2019-09-30.
 */
class GetRandomCase(private val quotationService: QuotationService) {
    suspend fun execute(): Result<Quotation> {
        return try {
            Result.Success(quotationService.getRandom())
        } catch (exception: Exception) {
            Result.Failure(
                if (exception is QuotationException) exception
                else QuotationException.UnknownQuotationException
            )
        }
    }
}