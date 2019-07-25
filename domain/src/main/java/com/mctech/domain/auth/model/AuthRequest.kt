package com.mctech.domain.auth.model

/**
 * @author MAYCON CARDOSO on 2019-07-25.
 */
data class AuthRequest(
    val type : AuthRequestType,
    val login : String,
    var password : String? = null
)

enum class AuthRequestType{
    EMAIL,
    GMAIL
}

