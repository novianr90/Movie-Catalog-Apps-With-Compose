package id.novian.challengechapter8.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Setup() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController = navController) }

        composable("login") { LoginScreen(navController = navController) }

        composable("register") { RegisterScreen(navController = navController) }

        composable("home") { HomeScreen(navController = navController) }

        composable("detail") { DetailScreen() }
    }
}