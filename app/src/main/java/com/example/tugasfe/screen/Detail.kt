package com.example.tugasfe.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
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
import com.example.tugasfe.R
import com.example.tugasfe.data.Movie
import com.example.tugasfe.ui.theme.Poppins
import com.example.tugasfe.ui.theme.gray
import com.example.tugasfe.ui.theme.green
import com.example.tugasfe.ui.theme.tosca
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun Detail(movie: Movie?, navController: NavHostController) {
    if (movie != null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .background(Color.Black)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 80.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(vertical = 18.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Tombol kembali
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .width(27.dp)
                            .height(25.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(26.dp)
                                .height(24.dp)
                        )
                    }
                    Text(
                        text = movie.judul,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Bold,
                        fontSize = 26.sp,
                        color = tosca,
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(shape = RoundedCornerShape(10.dp)) {
                        Image(
                            painter = painterResource(id = movie.foto),
                            contentDescription = "detail movie",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(180.dp)
                                .height(270.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = movie.judul,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "${movie.tahun} . ${movie.genre}",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        color = Color.White,
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    Image(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = "rating",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(100.dp)
                            .height(20.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Synopsis",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = movie.desc,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = Color.White,
                    textAlign = TextAlign.Justify,
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                BottomBar()
            }
        }
    } else {
        Text(
            text = "Movie not found",
            color = Color.White,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    }
}

@Composable
fun BottomBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .background(Color.Black),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PlayButton()
        FavButton()
    }
}

@Composable
fun PlayButton() {
    val isPlaying = remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .height(50.dp)
            .width(265.dp)
            .background(if (isPlaying.value) gray else green, CircleShape)
            .clickable(onClick = { isPlaying.value = !isPlaying.value }),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Play",
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.White,
        )
        Image(
            painter = painterResource(id = R.drawable.ic_play),
            contentDescription = "Play",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(30.dp)
                .padding(start = 10.dp)
        )
    }
}

@Composable
fun FavButton() {
    val isFavorited = remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .size(50.dp)
            .background(if (isFavorited.value) green else gray, CircleShape)
            .clickable(onClick = { isFavorited.value = !isFavorited.value }),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_fav),
            contentDescription = "Favorite",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(28.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailPreview() {
    // Membuat objek Movie dummy untuk menampilkan tampilan Detail
    val dummyMovie = Movie(
        id = 1,
        judul = "Movie Title",
        foto = R.drawable.ready,
        tahun = 2024,
        genre = "Action",
        desc = "This is a short description of the movie.aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    )
    val navController = rememberNavController()

    Detail(movie = dummyMovie, navController = navController)
}
