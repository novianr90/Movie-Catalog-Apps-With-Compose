package id.novian.challengechapter8.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.skydoves.landscapist.glide.GlideImage
import id.novian.challengechapter8.BuildConfig
import id.novian.challengechapter8.model.network.model.popular.Result
import id.novian.challengechapter8.viewmodel.HomeScreenViewModel

@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeScreenViewModel) {
    Surface(color = Color.White) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (txtUsername, txtHome, txtLogout, rvData) = createRefs()

            Text(
                text = "Welcome, Username!",
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
                    .clickable { navController.navigate("login") }
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

//            LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
//            modifier = Modifier
//                .constrainAs(rvData) {
//                    top.linkTo(txtHome.top, margin = 20.dp)
//                    start.linkTo(parent.start)
//                    end.linkTo(parent.end)
//                    bottom.linkTo(parent.bottom)
//                })
//            {
//                items()
//            }

        }
    }
}

@Composable
private fun ItemList(result: Result) {
    Card(
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 2.dp,
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth()
    ) {
        //Image
        val baseUrlImg = BuildConfig.BASE_URL_IMG
        val srcImg = "${baseUrlImg}${result.posterPath}"
        GlideImage(
            imageModel = srcImg,
            contentScale = ContentScale.Crop,

            )
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    val viewModel = hiltViewModel<HomeScreenViewModel>()
    HomeScreen(navController = rememberNavController(), viewModel)
}