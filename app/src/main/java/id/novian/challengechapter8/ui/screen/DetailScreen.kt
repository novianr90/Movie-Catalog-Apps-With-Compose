package id.novian.challengechapter8.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import id.novian.challengechapter8.viewmodel.DetailScreenViewModel

@Composable
fun DetailScreen(navController: NavHostController, viewModel: DetailScreenViewModel) {

}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    val viewModel = hiltViewModel<DetailScreenViewModel>()
    DetailScreen(rememberNavController(), viewModel)
}