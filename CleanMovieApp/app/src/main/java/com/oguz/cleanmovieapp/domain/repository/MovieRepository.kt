package com.oguz.cleanmovieapp.domain.repository

import com.oguz.cleanmovieapp.data.remote.dto.MovieDetailDto
import com.oguz.cleanmovieapp.data.remote.dto.MoviesDto

interface MovieRepository {

    /**
     * https://www.omdbapi.com/?apikey=1244443a&s=batman
     * @Query("s") searchString : String, burada s de neyi aradıgımızı soracak bize moviesDto döndürecek
     */
    suspend fun getMovies(search:String):MoviesDto

    suspend fun getMovieDetails(imdbId : String) : MovieDetailDto

}