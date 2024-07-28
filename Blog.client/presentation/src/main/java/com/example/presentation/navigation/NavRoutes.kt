package com.example.presentation.navigation

sealed class NavRoutes(val route: String) {
    data object BlogMainScreen: NavRoutes("blogMain")
    data object WriteBlogScreen: NavRoutes("writeBlog")
    data object SignInScreen: NavRoutes("signIn")
}