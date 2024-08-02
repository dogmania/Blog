package com.example.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.presentation.navigation.NavRoutes
import com.example.presentation.screens.blog.BlogMainScreen
import com.example.presentation.screens.blog.detail.BlogDetailScreen
import com.example.presentation.screens.blog.write.WriteBlogScreen
import com.example.presentation.screens.onboarding.signIn.SignInScreen
import com.example.presentation.screens.onboarding.signUp.SignUpScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    com.example.presentation.ui.theme.BlogclientTheme {
        val navController = rememberNavController()

        Scaffold(
            topBar = { null }
        ) { paddingValue ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = paddingValue.calculateTopPadding(),
                        bottom = paddingValue.calculateBottomPadding()
                    )
            ) {
                NavigationHost(navController = navController)
            }
        }
    }
}

@Composable
fun NavigationHost(
    navController: NavHostController
) {
    val popScreen: () -> Unit = { navController.popBackStack() }

    NavHost(
        navController = navController,
        startDestination = NavRoutes.SignInScreen.route
    ) {
        composable(NavRoutes.BlogMainScreen.route) {
            val goToWriteBlogScreen = {
                navController.navigate(NavRoutes.WriteBlogScreen.route)
            }
            val onClickArticleItem = { id: Long ->
                navController.navigate(NavRoutes.BlogDetailScreen.createRoute(id))
            }

            BlogMainScreen(
                goToWriteBlogScreen,
                onClickArticleItem = onClickArticleItem
            )
        }

        composable(NavRoutes.WriteBlogScreen.route) {
            WriteBlogScreen( popScreen )
        }

        composable(NavRoutes.SignInScreen.route) {
            val goToSignUp = {
                navController.navigate(NavRoutes.SignUpScreen.route)
            }
            val goToBlogMain = {
                navController.navigate(NavRoutes.BlogMainScreen.route)
            }

            SignInScreen(goToSignUp = goToSignUp, goToBlogMain = goToBlogMain)
        }

        composable(NavRoutes.SignUpScreen.route) {
            SignUpScreen(popScreen)
        }

        composable(
            "${ NavRoutes.BlogDetailScreen.route }/{id}",
            arguments = listOf(navArgument("id") { type = NavType.LongType } )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getLong("id") ?: 0
            
            BlogDetailScreen(id = id, popScreen = popScreen)
        }
    }
}