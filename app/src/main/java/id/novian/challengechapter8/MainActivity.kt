package id.novian.challengechapter8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import id.novian.challengechapter8.ui.screen.Setup
import id.novian.challengechapter8.ui.theme.ChallengeChapter8Theme
import id.novian.challengechapter8.viewmodel.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val splashViewModel: SplashViewModel by viewModels()
    private val homeScreenViewModel: HomeScreenViewModel by viewModels()
    private val detailScreenViewModel: DetailScreenViewModel by viewModels()
    private val loginScreenViewModel: LoginScreenViewModel by viewModels()
    private val registerScreenViewModel: RegisterScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChallengeChapter8Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Setup(
                        splashViewModel = splashViewModel,
                        homeScreenViewModel = homeScreenViewModel,
                        loginScreenViewModel = loginScreenViewModel,
                        registerScreenViewModel = registerScreenViewModel,
                        detailScreenViewModel = detailScreenViewModel
                    )
                }
            }
        }
    }
}