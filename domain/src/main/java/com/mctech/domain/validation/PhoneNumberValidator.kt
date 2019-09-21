package com.mctech.domain.validation


object PhoneNumberValidator{
    operator fun invoke(phoneNumber : String?) = phoneNumber?.let {
        true
    } ?: false
}