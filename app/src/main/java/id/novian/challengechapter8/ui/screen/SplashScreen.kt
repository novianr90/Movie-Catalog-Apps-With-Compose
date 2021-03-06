package id.novian.challengechapter8.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import id.novian.challengechapter8.R
import id.novian.challengechapter8.viewmodel.SplashViewModel
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavHostController, viewModel: SplashViewModel) {

    viewModel.getLogin()

    val isLogin: Boolean by viewModel.login.observeAsState(false)

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo_fox),
            contentDescription = "", modifier = Modifier
                .width(200.dp)
                .height(200.dp)
        )
    }


    LaunchedEffect(key1 = true) {

        //Navigate
        delay(2000L)
        if (isLogin) {
            navController.navigate("home") {
                popUpTo("splash") {
                    inclusive = true
                }
            }
        } else {
            navController.navigate("login") {
                popUpTo("splash") {
                    inclusive = true
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    val viewModel = hiltViewModel<SplashViewModel>()
    SplashScreen(rememberNavController(), viewModel)
}