package com.mctech.domain.errors

sealed class AuthException : RuntimeException(){
    object UserNotFoundException : AuthException()
    object WrongCredentialsException : AuthException()
    object NoAuthSessionFoundException : AuthException()
    object UnknownAuthException : AuthException()
}