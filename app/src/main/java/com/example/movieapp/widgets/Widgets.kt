package com.example.movieapp.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.movieapp.R
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovies

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieRow(movie: Movie = getMovies()[0], onClick: (Movie) -> Unit) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .clickable { onClick.invoke(movie) },
        elevation = 4.dp,
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier
                    .size(100.dp),
                shape = CircleShape,
                elevation = 4.dp,
            ) {
                CircleImage(url = movie.images[0])
            }
            Column(modifier = Modifier.padding(4.dp)) {
                Text(
                    modifier = Modifier.padding(5.dp), text = movie.title,
                    style = MaterialTheme.typography.h5
                )
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = "Director :- ${movie.director}",
                    style = MaterialTheme.typography.caption
                )
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = "Release :- ${movie.year}",
                    style = MaterialTheme.typography.caption
                )
                AnimatedVisibility(visible = expanded) {
                    Column {
                        Text(
                            modifier = Modifier.padding(5.dp),
                            text = "Plot :- ${movie.plot}",
                            style = MaterialTheme.typography.subtitle2
                        )
                        Divider(modifier = Modifier.padding(3.dp))
                        Text(
                            modifier = Modifier.padding(5.dp),
                            text = "Actors :- ${movie.actors}",
                            style = MaterialTheme.typography.subtitle1
                        )

                        Text(
                            modifier = Modifier.padding(5.dp),
                            text = "Rating :- ${movie.rating}",
                            style = MaterialTheme.typography.subtitle2
                        )
                    }
                }

                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    "",
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { expanded = !expanded },
                    tint = Color.DarkGray
                )


            }

        }
    }


}

@Composable
fun CircleImage(url: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true).transformations(CircleCropTransformation())
            .build(),
        contentDescription = "Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier.clip(CircleShape)
    )
}

@Composable
fun RectangleImage(url: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        contentDescription = "Image",
        contentScale = ContentScale.Fit,
        modifier = Modifier.clip(RectangleShape).padding(5.dp)
    )
}

@Composable
fun HorizontalImagesView(list: List<String>) {
    LazyRow() {
        items(list) {
            Card(
                modifier = Modifier
                    .padding(12.dp), elevation = 10.dp
            ) {
                RectangleImage(url = it)
            }
        }
    }
}