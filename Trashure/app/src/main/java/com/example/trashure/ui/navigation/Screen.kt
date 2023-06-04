package com.example.trashure.ui.navigation

sealed class Screen(val route: String) {
    object Home     : Screen("home")
    object Order    : Screen("order")
    object ScanPage : Screen("scan_page")
    object Inbox    : Screen("inbox")
    object Profile  : Screen("profile")
    object Splash1  : Screen("splash_1")
    object Splash2  : Screen("splash_2")
    object Login    : Screen("login")
    object Register : Screen("register")
}
