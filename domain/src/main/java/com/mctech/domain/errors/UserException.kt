package com.mctech.domain.errors

sealed class UserException : RuntimeException(){
    object AlreadyRegisteredUserException : UserException()
    object InvalidEmailFormatException : UserException()
    object InvalidPhoneNumberFormatException : UserException()
}