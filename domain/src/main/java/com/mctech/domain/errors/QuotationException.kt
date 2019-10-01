package com.mctech.domain.errors

/**
 * @author MAYCON CARDOSO on 2019-09-30.
 */
sealed class QuotationException : Exception(){
    object UnknownQuotationException     : QuotationException()
    object ConnectionIssueException      : QuotationException()
}