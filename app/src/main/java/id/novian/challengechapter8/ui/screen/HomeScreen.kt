package id.novian.challengechapter8.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen(navController: NavHostController) {
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

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}