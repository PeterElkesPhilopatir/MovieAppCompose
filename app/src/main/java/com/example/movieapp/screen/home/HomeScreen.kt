package com.example.movieapp.screen.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovies
import com.example.movieapp.navigation.MovieScreens
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.widgets.MovieRow

@Composable
fun HomeScreen(navController: NavController) {
    MainContent(navController)
}

@Composable
fun MainContent(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = MaterialTheme.colors.primary, elevation = 4.dp) {
                Text("Movies")
            }
        },
    ) {
        Surface {
            MoviesList(
                navController = navController,
                list = getMovies()
            )
        }
    }
}

@Composable
fun MoviesList(list: List<Movie>, navController: NavController) {
    Column(modifier = Modifier.padding(12.dp)) {
        LazyColumn {
            items(items = list) {
                MovieRow(it) { movie ->
                    Log.i("ClickedMovie", movie.title)
                    navController.navigate(MovieScreens.DetailsScreen.name + "/${movie.id}")
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