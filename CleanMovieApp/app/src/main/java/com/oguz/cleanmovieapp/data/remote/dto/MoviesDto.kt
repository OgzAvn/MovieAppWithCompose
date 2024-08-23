package com.oguz.cleanmovieapp.data.remote.dto

import com.oguz.cleanmovieapp.domain.model.Movie

data class MoviesDto(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)

//Extension->
/**
 * Bize bir DTO gelecek ve Api den gelince buradaki fun(toMovieList) ı çağıracagız o da bize list of movie verecek.
 */
fun MoviesDto.toMovieList() : List<Movie> {

    return Search.map {search-> Movie(search.Poster,search.Title,search.Year,search.imdbID) }
    //TODO: map -> bir objeyi başka bir objeye transform etmeye yarıyor.
    // search diye bir obje gelecek bana bunu Movie e çevireceğim.
}