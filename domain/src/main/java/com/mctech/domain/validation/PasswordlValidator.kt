package com.mctech.domain.validation


object PasswordlValidator{
    operator fun invoke(password : String?) = password?.let {
        password.length >= 5
    } ?: false
}