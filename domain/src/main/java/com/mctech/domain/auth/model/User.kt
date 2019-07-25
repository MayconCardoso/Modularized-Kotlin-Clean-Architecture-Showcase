package com.mctech.domain.auth.model

/**
 * @author MAYCON CARDOSO on 2019-07-25.
 */
data class User(
    val id: String,
    val phoneNumber: String,
    val name: String,
    var profilePicture: String
)