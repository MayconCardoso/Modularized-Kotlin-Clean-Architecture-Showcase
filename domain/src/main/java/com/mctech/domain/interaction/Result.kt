package com.mctech.domain.interaction

sealed class Result {
    data class Success<T>(val result: T) : Result()
    data class Failure(val throwable: Throwable) : Result()
}