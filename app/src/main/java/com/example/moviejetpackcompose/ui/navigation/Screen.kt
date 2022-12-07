package com.example.moviejetpackcompose.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("list")
    object Detail : Screen("detail/{detailId}") {
        fun createRoute(rewardId: Long) = "detail/$rewardId"
    }
    object About : Screen("about")
    object Favorite : Screen("favorite")
}