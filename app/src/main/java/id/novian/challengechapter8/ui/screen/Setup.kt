package id.novian.challengechapter8.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import id.novian.challengechapter8.viewmodel.*

@Composable
fun Setup(
    splashViewModel: SplashViewModel,
    homeScreenViewModel: HomeScreenViewModel,
    loginScreenViewModel: LoginScreenViewModel,
    registerScreenViewModel: RegisterScreenViewModel,
    detailScreenViewModel: DetailScreenViewModel
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(
                navController = navController,
                viewModel = splashViewModel
            )
        }

        composable("login") {
            LoginScreen(
                navController = navController,
                viewModel = loginScreenViewModel
            )
        }

        composable("register") {
            RegisterScreen(
                navController = navController,
                viewModel = registerScreenViewModel
            )
        }

        composable("home") {
            HomeScreen(
                navController = navController,
                viewModel = homeScreenViewModel
            )
        }

        composable("detail") {
            DetailScreen(
                navController = navController,
                viewModel = detailScreenViewModel
            )
        }
    }
}