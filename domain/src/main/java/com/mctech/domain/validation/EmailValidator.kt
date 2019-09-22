package com.mctech.domain.validation

import java.util.regex.Pattern


object EmailValidator{
    private val pattern = Pattern.compile(
        "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$"
    )

    operator fun invoke(email : String?) = email?.let {
        return pattern.matcher(email).matches()
    } ?: false


}