package id.novian.challengechapter8.ui.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.skydoves.landscapist.glide.GlideImage
import id.novian.challengechapter8.BuildConfig
import id.novian.challengechapter8.helper.toDate
import id.novian.challengechapter8.viewmodel.HomeScreenViewModel

@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeScreenViewModel) {

    val context = LocalContext.current

    //Insert list of movies and get Data
    if (viewModel.movieState.isEmpty()) {
        viewModel.insertMovie()
    }
    viewModel.getMovie()
    viewModel.getUsername()

    if (viewModel.usernameError.isNotEmpty()) {
        Toast.makeText(context, viewModel.usernameError, Toast.LENGTH_SHORT).show()
    }

    Surface(color = Color.White) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (txtUsername, txtHome, txtLogout, rvData) = createRefs()

            Text(
                text = "Welcome, ${viewModel.username}",
                fontSize = 16.sp,
                modifier = Modifier
                    .constrainAs(txtUsername) {
                        top.linkTo(parent.top, margin = 32.dp)
                        start.linkTo(parent.start, margin = 20.dp)
                    }
            )

            Text(
                text = "Logout",
                fontSize = 16.sp,
                modifier = Modifier
                    .constrainAs(txtLogout) {
                        top.linkTo(parent.top, margin = 32.dp)
                        end.linkTo(parent.end, margin = 20.dp)
                    }
                    .clickable {
                        navController.navigate("login") {
                            popUpTo("home") {
                                inclusive = true
                            }
                        }; viewModel.sessionClear()
                    }
            )

            Text(
                text = "Home",
                fontSize = MaterialTheme.typography.h5.fontSize,
                modifier = Modifier
                    .constrainAs(txtHome) {
                        top.linkTo(parent.top, margin = 88.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            val baseImageUrl = BuildConfig.BASE_URL_IMG

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .constrainAs(rvData) {
                        top.linkTo(txtHome.bottom, margin = 8.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxSize(),
                contentPadding = PaddingValues(
                    start = 12.dp,
                    top = 4.dp,
                    end = 12.dp,
                    bottom = 4.dp
                ),
                content = {
                    items(viewModel.movieState.size) { index ->
                        Column(modifier = Modifier
                            .fillMaxSize()
                            .clickable { navController.navigate("detail/${viewModel.movieState[index].idMovie}") }
                            .padding(start = 8.dp, top = 8.dp)) {

                            Card(
                                modifier = Modifier.fillMaxSize(),
                                shape = RoundedCornerShape(4.dp)
                            ) {
                                GlideImage(
                                    imageModel = "${baseImageUrl}${viewModel.movieState[index].posterPath}",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .width(400.dp)
                                        .height(300.dp)
                                )
                            }

                            Text(
                                text = viewModel.movieState[index].title, modifier = Modifier
                                    .padding(start = 4.dp)
                            )

                            viewModel.movieState[index].releaseDate.toDate()
                                ?.let {
                                    Text(
                                        text = it, modifier = Modifier
                                            .padding(start = 4.dp)
                                    )
                                }

                        }
                    }
                })

        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val viewModel = hiltViewModel<HomeScreenViewModel>()
    HomeScreen(navController = rememberNavController(), viewModel)
}