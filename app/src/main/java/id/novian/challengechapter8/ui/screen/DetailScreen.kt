package id.novian.challengechapter8.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.skydoves.landscapist.glide.GlideImage
import id.novian.challengechapter8.BuildConfig
import id.novian.challengechapter8.helper.toDate
import id.novian.challengechapter8.viewmodel.DetailScreenViewModel

@Composable
fun DetailScreen(id: Int, viewModel: DetailScreenViewModel) {
    //Get Details
    viewModel.getDetailsMovieById(id)

    val data = viewModel.data.observeAsState()

    val baseImgUrl = BuildConfig.BASE_URL_IMG

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (backdrop, poster, title, genre, tagline, date, runtime, overview) = createRefs()

        //Backdrop and Poster
        Box(modifier = Modifier
            .constrainAs(backdrop) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {
            GlideImage(
                imageModel = "${baseImgUrl}${data.value?.backdropPath}",
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
            )
        }

        Card(shape = RoundedCornerShape(10.dp), modifier = Modifier
            .width(150.dp)
            .height(250.dp)
            .constrainAs(poster) {
                top.linkTo(backdrop.bottom, margin = (-80).dp)
                start.linkTo(parent.start, margin = 16.dp)
            }
        ) {
            GlideImage(
                imageModel = "${baseImgUrl}${data.value?.posterPath}"
            )
        }

        //Title
        Text(
            text = data.value?.title ?: "Movie Title",
            fontSize = MaterialTheme.typography.h6.fontSize,
            maxLines = 4,
            textAlign = TextAlign.Justify,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .width(180.dp)
                .constrainAs(title) {
                    top.linkTo(backdrop.bottom, margin = 4.dp)
                    start.linkTo(poster.end, margin = 15.dp)
                }
        )

        //Date
        Text(
            text = data.value?.releaseDate?.toDate() ?: "01-01-1970",
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp,
            modifier = Modifier
                .constrainAs(date) {
                    start.linkTo(poster.end, margin = 15.dp)
                    top.linkTo(title.bottom, margin = 4.dp)
                }

        )

        //Runtime
        val hour = (data.value?.runtime?.div(60)).toString()
        val minutes = (data.value?.runtime?.rem(60)).toString()

        val runtimeMovie = "${hour}h ${minutes}m"

        Text(
            text = runtimeMovie,
            fontSize = 13.sp,
            modifier = Modifier
                .constrainAs(runtime) {
                    start.linkTo(date.end, margin = 10.dp)
                    top.linkTo(title.bottom, margin = 4.dp)
                }
        )

        //Genre
        var genres = ""

        val genreResult = data.value?.genres

        if (genreResult != null) {
            if (genreResult.size == 1) {
                genres += genreResult[0].name
            } else {
                for (i in genreResult.indices) {
                    genres += if (i == genreResult.lastIndex) {
                        genreResult[i].name
                    } else {
                        genreResult[i].name + ", "
                    }
                }
            }
        }

        Text(
            text = genres,
            maxLines = 2,
            textAlign = TextAlign.Justify,
            fontSize = 11.sp,
            modifier = Modifier
                .constrainAs(genre) {
                    top.linkTo(date.bottom, margin = 4.dp)
                    start.linkTo(poster.end, margin = 15.dp)
                }
        )

        //Tagline
        Text(
            text = data.value?.tagline ?: "Tagline",
            maxLines = 2,
            fontSize = 16.sp,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .width(180.dp)
                .constrainAs(tagline) {
                    top.linkTo(genre.bottom, margin = 20.dp)
                    start.linkTo(poster.end, margin = 15.dp)
                }
        )

        //Overview
        Text(
            text = data.value?.overview ?: "Overview",
            maxLines = 15,
            textAlign = TextAlign.Justify,
            fontSize = 18.sp,
            modifier = Modifier
                .constrainAs(overview) {
                    top.linkTo(poster.bottom, margin = 12.dp)
                }
                .padding(start = 12.dp, end = 12.dp)
        )


    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    val viewModel = hiltViewModel<DetailScreenViewModel>()
    DetailScreen(0, viewModel)
}