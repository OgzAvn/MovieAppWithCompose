package com.oguz.cleanmovieapp.presentation.movie_detail


import com.oguz.cleanmovieapp.domain.model.MovieDetail

data class MovieDetailState(
    val isLoading : Boolean = false,
    val movies : MovieDetail? = null,
    val error : String = "",
)
