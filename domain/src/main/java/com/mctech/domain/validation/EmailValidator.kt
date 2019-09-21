package com.mctech.domain.validation


object EmailValidator{
    operator fun invoke(email : String?) = email?.let {
        true
    } ?: false
}