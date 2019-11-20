package com.mctech.data.quotation.model

import com.google.gson.annotations.SerializedName

/**
 * @author MAYCON CARDOSO on 2019-11-06.
 */
class RandomQuotationEmbeddedResponse(
    @SerializedName("author")
    val author: List<QuotationAuthorResponse>,

    @SerializedName("source")
    val source: List<QuotationSourceResponse>
)