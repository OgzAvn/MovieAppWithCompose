package com.oguz.cleanmovieapp.presentation.movies.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.oguz.cleanmovieapp.presentation.Screen
import com.oguz.cleanmovieapp.presentation.movies.MoviesEvent
import com.oguz.cleanmovieapp.presentation.movies.MoviesViewModel


@Composable
fun MovieScreen(
    navController: NavController,
    viewModel: MoviesViewModel = hiltViewModel()
) {

    //TODO:Ekran loading demi error mu aldı movie ler geldi mi hepsini görebiliriz.
    val state = viewModel.state.value


    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)

    )
    {
        Column {

            MovieSearchBar(modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
                hint = "Batman",
                onSearch = {
                    viewModel.onEvent(MoviesEvent.Search(it))
                }
            )

            LazyColumn(modifier = Modifier.fillMaxSize()) {

                items(state.movies){movie->
                   //TODO:MovieList leri göstereceğimiz bir row yapacağız.
                    
                    MovieListRow(movie = movie, onItemClick = {

                        navController.navigate(Screen.MovieDetailScreen.route + "/${movie.imdbID}")
                        //movie_detail_screen/imdbId aslında böyle yolluyorum

                    })

                }
            }
        }

    }


}

@Preview
@Composable
fun MovieScreenPreview(){
    MovieScreen(navController = rememberNavController())
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieSearchBar(
    modifier: Modifier,
    hint : String = "",
    onSearch : (String) ->Unit = {}
) {

    //TODO:SearhBar da batman yazısı gösterilioyr mu onu kontrol edeceğim

    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    var text by remember {
        mutableStateOf("")
    }

    Box(modifier = modifier){
        TextField(value = text, onValueChange = {

            text = it
        },
            keyboardActions = KeyboardActions(onDone = {
                onSearch(text)
                /**
                 * onSearch = { viewModel.onEvent(MoviesEvent.Search(it)) }
                 * buradaki it işte bu text burdan oraya gidiyor. MovieSearchBar ın içerisine
                 */
            }),

            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                containerColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp)
                .onFocusChanged { focusState ->
                    // Handle focus changes if needed
                    isHintDisplayed = focusState.isFocused != true && text.isEmpty()
                    //Kullanıcı search bar a tıkladıgında benım orada bısey gostermemem lazım

                }

        )

        if (isHintDisplayed){
            Text(text = hint,
                color = Color.LightGray,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp))
        }

    }

    
}

