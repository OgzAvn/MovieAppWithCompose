package com.oguz.cleanmovieapp.data.remote

import com.oguz.cleanmovieapp.data.remote.dto.MovieDetailDto
import com.oguz.cleanmovieapp.data.remote.dto.MoviesDto
import com.oguz.cleanmovieapp.util.Constant.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    /**
     * https://www.omdbapi.com/?apikey=1244443a&i=tt0372784
     * https://www.omdbapi.com/?apikey=1244443a&s=batman
     */


    @GET(".")
    suspend fun getMovies(
        @Query("s") searchString : String,
        @Query("apikey") apiKey : String = API_KEY
    ) : MoviesDto


    @GET(".")
    suspend fun getMovieDetail(
        @Query("i") imdbId : String,
        @Query("apikey") apiKey : String = API_KEY
    ) : MovieDetailDto


}