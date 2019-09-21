package com.mctech.domain.errors

sealed class AuthException : RuntimeException(){
    object UserNotFoundException : AuthException()
    object EmptyFormValueException : AuthException()
    object WrongCredentialsException : AuthException()
    object NoAuthSessionFoundException : AuthException()
    object PasswordUnderFiveCharactersException : AuthException()
    object UnknownAuthException : AuthException()

    object AlreadyRegisteredUserException : AuthException()
    object InvalidEmailFormatException : AuthException()
    object InvalidPhoneNumberFormatException : AuthException()
}