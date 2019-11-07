package com.mctech.domain.model

import java.util.*

/**
 * @author MAYCON CARDOSO on 2019-09-30.
 */
data class Quotation(
    val id : String,
    val description: String,
    val date: Date,
    val tag: List<String>,
    val author: String,
    val twitterLink: String
)