package com.example.movieapp.screen.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovies
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.widgets.HorizontalImagesView
import com.example.movieapp.widgets.MovieRow
import com.example.movieapp.widgets.RectangleImage

@Composable
fun DetailsScreen(navController: NavController, movie: String?) {
    MainContent(navController, movie)
}

@Composable
fun MainContent(navController: NavController, movieID: String?) {
    val movie = getMovies().first { it.id == movieID }
    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = MaterialTheme.colors.primary, elevation = 4.dp) {
                Row(horizontalArrangement = Arrangement.Start) {

                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier
                            .clickable {
                                navController.popBackStack()
                            }
                            .padding(end = 10.dp)
                    )
                    Text("Movies")
                }
            }
        },
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                MovieRow(onClick = {}, movie = movie)
                Text(
                    movie.title,
                    style = TextStyle(fontWeight = FontWeight.ExtraBold, fontSize = 40.sp)
                )
                Text(
                    "Movie Images",
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
                )
                HorizontalImagesView(movie.images)
                Spacer(modifier = Modifier.height(50.dp))
                Button(onClick = { navController.popBackStack() }) {
                    Text("Go Back")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieAppTheme {
    }
}