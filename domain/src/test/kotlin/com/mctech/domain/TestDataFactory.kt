package com.mctech.domain

import com.mctech.domain.model.AuthRequest
import com.mctech.domain.model.Quotation
import com.mctech.domain.model.RegisterUser
import com.mctech.domain.model.User
import java.util.*

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
        id : String = "",
        tag : String = "",
        description: String = "",
        date : Date = Date(),
        author : String = "",
        twitterLink : String = ""
    ) = Quotation(
        id = id,
        description = description,
        date = date,
        author = author,
        twitterLink = twitterLink,
        tag = listOf(tag)
    )
}