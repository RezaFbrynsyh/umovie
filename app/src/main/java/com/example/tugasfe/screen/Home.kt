package com.example.tugasfe.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tugasfe.ui.theme.Poppins
import com.example.tugasfe.R
import com.example.tugasfe.data.Movie
import com.example.tugasfe.data.MovieData


@Composable
fun Home(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp)
            .background(Color.Black)
    ){
        Image(
            painter = painterResource(id = R.drawable.logo2),
            contentDescription = "home",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 18.dp)
                .height(55.dp),
            contentScale = ContentScale.Fit
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(15.dp),
        ) {
            item {
                Text(
                    text = "Trending Now",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(15.dp),

                ) {
                    items(MovieData.movie.subList(10, 20), key = { it.id }) { movie ->
                        MovieCardRow(movie = movie, navController = navController)
                    }
                }
            }
            item {
                Text(
                    text = "For You",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            items(MovieData.movie.take(10), key = { it.id }) { movie ->
                MovieCardColumn(movie = movie, navController = navController)
            }
        }
    }
}

@Composable
fun MovieCardRow(movie: Movie, navController: NavHostController) {
    Card(
        modifier = Modifier
            .width(120.dp)
            .height(178.dp)
            .clickable {
                navController.navigate("detail/${movie.id}")
            },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray),
    ) {
        Image(
            painter = painterResource(id = movie.foto),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun MovieCardColumn(movie: Movie, navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("detail/${movie.id}")
            }
    ){
        Card(
            modifier = Modifier
                .width(160.dp)
                .height(235.dp),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = Color.DarkGray),
        ) {
            Image(
                painter = painterResource(id = movie.foto),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .fillMaxWidth()
                .align(Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = movie.judul,
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Text(
                text = "${movie.tahun} . ${movie.genre}",
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = Color.White,
            )
            Image(
                painter = painterResource(id = R.drawable.star),
                contentDescription = "rating",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(100.dp)
                    .height(20.dp)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun HomePreview() {
    val navController = rememberNavController()
    Home(navController = navController)
}


