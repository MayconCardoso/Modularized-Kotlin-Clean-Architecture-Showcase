package com.mctech.domain.model

/**
 * @author MAYCON CARDOSO on 2019-07-25.
 */
data class User(
    val id: String,
    val name: String,
    val phoneNumber: String?,
    val email: String?,
    var profilePicture: String?
)

fun User.checkEmail() : Boolean {
    return true
}

fun User.checkPhoneNumber() : Boolean {
    return true
}