package com.oguz.cleanmovieapp.data.remote.dto

import com.oguz.cleanmovieapp.domain.model.MovieDetail

data class MovieDetailDto(
    val Actors: String,
    val Awards: String,
    val Country: String,
    val Director: String,
    val Genre: String,
    val Language: String,
    val Poster: String,
    val Rated: String,
    val Released: String,
    val Title: String,
    val Year: String,
    val imdbRating: String,
)

fun MovieDetailDto.toMovieDetail() : MovieDetail{
    return MovieDetail(Actors, Awards, Country, Director, Genre, Language, Poster, Rated, Released, Title, Year, imdbRating)
}