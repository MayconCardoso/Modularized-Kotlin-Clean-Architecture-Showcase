package com.mctech.features.navigation

data class UnsupportedNavigation(val destination: Screen) : RuntimeException(
    "Cannot navigate to this destination -> $destination"
)