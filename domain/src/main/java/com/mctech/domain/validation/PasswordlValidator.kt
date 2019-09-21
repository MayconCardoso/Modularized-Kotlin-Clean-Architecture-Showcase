package com.mctech.domain.validation


object PasswordlValidator{
    operator fun invoke(email : String?) = email?.let {
        true
    } ?: false
}