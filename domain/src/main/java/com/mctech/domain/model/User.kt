package com.mctech.domain.model

/**
 * @author MAYCON CARDOSO on 2019-07-25.
 */
data class User(
    var id: String? = "",
    val name: String,
    val email: String?,
    var profilePicture: String? = ""
)