package com.example.trashure.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Order : Screen("order")
    object ScanPage : Screen("scan_page")
    object Inbox : Screen("inbox")
    object Profile : Screen("profile")
}
