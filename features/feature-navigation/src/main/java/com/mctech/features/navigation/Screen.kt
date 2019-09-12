package com.mctech.features.navigation

sealed class Screen {
    object Login : Screen()
    object Dashboard : Screen()

    override fun toString() = when (this) {
        Login -> "Login Screen"
        Dashboard -> "Dashboard Screen"
    }
}