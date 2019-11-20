package com.mctech.domain.errors

sealed class AuthException : RuntimeException(){
    object UserNotFoundException : AuthException()
    object EmptyFormValueException : AuthException()
    object WrongCredentialsException : AuthException()
    object NoAuthSessionFoundException : AuthException()
    object PasswordUnderSixCharactersException : AuthException()
    object PasswordsDoNotMatchException : AuthException()
    object UnknownAuthException : AuthException()

    object AlreadyRegisteredUserException : AuthException()
    object InvalidEmailFormatException : AuthException()
}