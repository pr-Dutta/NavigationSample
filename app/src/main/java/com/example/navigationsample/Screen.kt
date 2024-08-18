package com.example.navigationsample

sealed class Screen(val rout: String) {
    object Home: Screen("home")
    object Settings: Screen("settings")
    object Details: Screen("details/{itemId}") {
        // This function will provide the correct rout with the itemId parameter
        fun createRoute(itemId: String) = "details/{itemId}"
    }
}