package com.mctech.features.navigation

sealed class Screen {
    object Splash : Screen()
    object Login : Screen()
    object Dashboard : Screen()

    override fun toString() = when (this) {
        Splash -> "Spash Screen"
        Login -> "Login Screen"
        Dashboard -> "Dashboard Screen"
    }
}