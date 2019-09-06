package com.mctech.domain.model

/**
 * @author MAYCON CARDOSO on 2019-07-25.
 */
data class RegisterUser(val user: User, val password: String)

fun RegisterUser.checkPassword() : Boolean {
    return true
}