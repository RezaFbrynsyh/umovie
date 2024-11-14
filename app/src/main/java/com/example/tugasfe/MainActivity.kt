package com.example.tugasfe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tugasfe.ui.theme.TugasFETheme
import com.example.tugasfe.componen.Screens
import com.example.tugasfe.data.MovieData
import com.example.tugasfe.screen.Detail
import com.example.tugasfe.screen.Favorite
import com.example.tugasfe.screen.Home
import com.example.tugasfe.screen.Profile
import com.example.tugasfe.screen.Search
import com.example.tugasfe.ui.theme.tosca
import androidx.compose.ui.Alignment
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.currentBackStackEntryAsState


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            TugasFETheme {
                BottomBar()
            }
        }
    }
}

@Composable
fun BottomBar() {
    val navController = rememberNavController()
    val selectedIcon = remember { mutableStateOf(R.drawable.ic_home) }

    // Mendapatkan route saat ini
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    Scaffold(
        containerColor = Color.Black,
        bottomBar = {
            if (currentRoute != "detail/{movieId}") {
                BottomAppBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp),
                    containerColor = Color.DarkGray.copy(alpha = 0.8f),
                ) {
                    IconButton(
                        onClick = {
                            selectedIcon.value = R.drawable.ic_home
                            navController.navigate(Screens.Home.screen) {
                                popUpTo(0)
                            }
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_home),
                            contentDescription = null,
                            tint = if (selectedIcon.value == R.drawable.ic_home) tosca else Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    IconButton(
                        onClick = {
                            selectedIcon.value = R.drawable.ic_search
                            navController.navigate(Screens.Search.screen) {
                                popUpTo(0)
                            }
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = null,
                            tint = if (selectedIcon.value == R.drawable.ic_search) tosca else Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    IconButton(
                        onClick = {
                            selectedIcon.value = R.drawable.ic_fav
                            navController.navigate(Screens.Favorite.screen) {
                                popUpTo(0)
                            }
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_fav),
                            contentDescription = null,
                            tint = if (selectedIcon.value == R.drawable.ic_fav) tosca else Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    IconButton(
                        onClick = {
                            selectedIcon.value = R.drawable.ic_profile
                            navController.navigate(Screens.Profile.screen) {
                                popUpTo(0)
                            }
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_profile),
                            contentDescription = null,
                            tint = if (selectedIcon.value == R.drawable.ic_profile) tosca else Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        val movies = MovieData.movie

        NavHost(
            navController = navController,
            startDestination = Screens.Home.screen,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color.Black)
        ) {
            composable(Screens.Home.screen) {
                Home(navController = navController)
            }
            composable(Screens.Search.screen) {
                Search(movies = movies, navController = navController)
            }
            composable(Screens.Favorite.screen) {
                Favorite(navController = navController)
            }
            composable(Screens.Profile.screen) {
                Profile(navController = navController)
            }
            composable(
                route = "detail/{movieId}",
                arguments = listOf(navArgument("movieId") { type = NavType.IntType })
            ) { backStackEntry ->
                val movieId = backStackEntry.arguments?.getInt("movieId") ?: 0
                val movie = MovieData.movie.firstOrNull { it.id == movieId }

                if (movie != null) {
                    Detail(movie = movie, navController = navController)
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
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun GreetingPreview() {
    TugasFETheme {
        BottomBar()
    }
}
