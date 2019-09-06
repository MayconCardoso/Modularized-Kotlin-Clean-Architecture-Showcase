package com.mctech.domain.model

/**
 * @author MAYCON CARDOSO on 2019-07-25.
 */
data class AuthRequest(
    val type : AuthRequestType,
    val login : String,
    var password : String
)

enum class AuthRequestType{
    EMAIL
}

