package com.oguz.cleanmovieapp.domain.use_case.get_movie_detail

import coil.network.HttpException
import com.oguz.cleanmovieapp.data.remote.dto.toMovieDetail
import com.oguz.cleanmovieapp.domain.model.MovieDetail
import com.oguz.cleanmovieapp.domain.repository.MovieRepository
import com.oguz.cleanmovieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOError
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    fun executeGetMovieDetails(imdbId: String) : Flow<Resource<MovieDetail>> = flow {

        try {
            emit(Resource.Loading())
            val movieDetail = repository.getMovieDetails(imdbId)
            emit(Resource.Success(movieDetail.toMovieDetail()))
        }catch (e : IOError){
            emit(Resource.Error("No Internet Connection"))
        }
        catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "No Data"))
        }

    }
}