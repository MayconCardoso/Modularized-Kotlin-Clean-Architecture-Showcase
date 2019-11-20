package com.mctech.feature.arq

sealed class ComponentState<out T> {
    object Initializing : ComponentState<Nothing>()
    object Loading : ComponentState<Nothing>()
    data class Error(val reason: Throwable) : ComponentState<Nothing>()
    data class Success<T>(val result: T) : ComponentState<T>()
}