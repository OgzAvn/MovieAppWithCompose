package com.oguz.cleanmovieapp.data.repository

import com.oguz.cleanmovieapp.data.remote.MovieApi
import com.oguz.cleanmovieapp.data.remote.dto.MovieDetailDto
import com.oguz.cleanmovieapp.data.remote.dto.MoviesDto
import com.oguz.cleanmovieapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api : MovieApi
) : MovieRepository{


    override suspend fun getMovies(search: String): MoviesDto {
        return api.getMovies(searchString = search)
    }

    override suspend fun getMovieDetails(imdbId: String): MovieDetailDto {
        return api.getMovieDetail(imdbId = imdbId)
    }
}