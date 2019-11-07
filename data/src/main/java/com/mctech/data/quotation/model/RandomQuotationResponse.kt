package com.mctech.data.quotation.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * @author MAYCON CARDOSO on 2019-11-06.
 */
class RandomQuotationResponse(

    @SerializedName("quote_id")
    val id: String,

    @SerializedName("appeared_at")
    val date: Date,

    @SerializedName("tags")
    val tags: List<String>,

    @SerializedName("value")
    val description: String

)