package com.oguz.cleanmovieapp.presentation.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.oguz.cleanmovieapp.presentation.Screen
import com.oguz.cleanmovieapp.presentation.movie_detail.views.MovieDetailScreen
import com.oguz.cleanmovieapp.presentation.movies.views.MovieScreen
import com.oguz.cleanmovieapp.presentation.theme.ui.CleanMovieAppTheme
import com.oguz.cleanmovieapp.util.Constant.IMDB_ID
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanMovieAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = Screen.MovieScreen.route){

                        composable(route = Screen.MovieScreen.route){
                            MovieScreen(navController = navController)
                        }

                        composable(Screen.MovieDetailScreen.route+"/{${IMDB_ID}}"){
                            //movie_detail_Screen/imdb_id
                            MovieDetailScreen()

                        }
                    }

                }
            }
        }
    }
}

