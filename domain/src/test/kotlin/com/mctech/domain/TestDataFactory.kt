package com.mctech.domain

import com.mctech.domain.model.AuthRequest
import com.mctech.domain.model.Quotation
import com.mctech.domain.model.RegisterUser
import com.mctech.domain.model.User

object TestDataFactory{
    fun createAuthRequest(email: String) = AuthRequest(
        email = email,
        password = ""
    )

    fun createRegisterUserRequest(
        email: String = "",
        name : String = "",
        password : String = "",
        passwordConfirmation : String = ""
    ) = RegisterUser(
        user = User(
            name = name,
            email = email
        ),
        password = password,
        passwordConfirmation = passwordConfirmation
    )

    fun createQuotation(
        tag : String = "",
        description: String = "",
        date : String = "",
        author : String = "",
        twitterLink : String = ""
    ) = Quotation(
        description = description,
        date = date,
        author = author,
        twitterLink = twitterLink,
        tag = tag
    )
}