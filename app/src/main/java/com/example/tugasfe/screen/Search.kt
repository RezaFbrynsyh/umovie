package com.example.tugasfe.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tugasfe.R
import com.example.tugasfe.data.Movie
import com.example.tugasfe.data.MovieData
import com.example.tugasfe.ui.theme.Poppins
import com.example.tugasfe.ui.theme.tosca

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(movies: List<Movie>, navController: NavHostController) {
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .background(Color.Black)
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Search",
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                color = tosca,
            )
            Image(
                painter = painterResource(id = R.drawable.search),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .width(26.dp)
                    .height(24.dp)
            )
        }
        // Search Bar
        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            placeholder = { Text("Search Movie", color = Color.Gray) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp)
                .background(Color.DarkGray, shape = RoundedCornerShape(40.dp)),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.White
            ),
            shape = RoundedCornerShape(40.dp),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(24.dp)
                )
            }
        )
        val filteredMovies = if (searchText.isEmpty()) {
            movies
        } else {
            movies.filter {
                it.judul.contains(searchText, ignoreCase = true)
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(filteredMovies, key = { it.id }) { movie ->
                MovieCardGrid(movie = movie, navController = navController)
            }
        }
    }
}

@Composable
fun MovieCardGrid(movie: Movie, navController: NavHostController) {
    Card(
        modifier = Modifier
            .width(120.dp)
            .height(220.dp)
            .clickable {
                navController.navigate("detail/${movie.id}")
            },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray),
    ) {
        Image(
            painter = painterResource(id = movie.foto),
            contentDescription = "poster",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun SearchPreview() {
    val navController = rememberNavController()
    Search(movies = MovieData.movie, navController = navController)
}

