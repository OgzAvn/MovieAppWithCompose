package com.oguz.cleanmovieapp.presentation.movie_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oguz.cleanmovieapp.domain.use_case.get_movie_detail.GetMovieDetailsUseCase
import com.oguz.cleanmovieapp.util.Constant.IMDB_ID
import com.oguz.cleanmovieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val stateHandle: SavedStateHandle

) :ViewModel(){


    private val _state = mutableStateOf<MovieDetailState>(MovieDetailState())
    val state : State<MovieDetailState> = _state


    //TODO:Imbd Id yi viewmodel içerisinde nasıl alacağım - > SavedStateHandle
    init {

        stateHandle.get<String>(IMDB_ID)?.let {
            getMovieDetail(it)
        }
    }

    private fun getMovieDetail(imdbId : String){

        getMovieDetailsUseCase.executeGetMovieDetails(imdbId = imdbId).onEach {

            when(it){

                is Resource.Success ->{
                    _state.value = MovieDetailState(movies = it.data)
                }

                is Resource.Loading ->{
                    _state.value = MovieDetailState(isLoading = true)
                }

                is Resource.Error ->{
                    _state.value = MovieDetailState(error = it.message ?: "No Data")
                }

            }
        }.launchIn(viewModelScope)


    }
}