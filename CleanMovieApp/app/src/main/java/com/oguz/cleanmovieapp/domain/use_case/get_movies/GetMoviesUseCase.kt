package com.oguz.cleanmovieapp.domain.use_case.get_movies

import coil.network.HttpException
import com.oguz.cleanmovieapp.data.remote.dto.toMovieList
import com.oguz.cleanmovieapp.domain.model.Movie
import com.oguz.cleanmovieapp.domain.repository.MovieRepository
import com.oguz.cleanmovieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOError
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    /**
     * UseCase de sadece 1 adet fun yazılır ve public olur eger birden fazla fun varsa ikiye bölmeyi düşünebilirsiniz.
     * Burada sadece movie leri download fun yazacagız
     */

    fun executeGetMovies(search: String) : Flow<Resource<List<Movie>>> = flow {

        try {
            emit(Resource.Loading())
            val movieList = repository.getMovies(search)
            if (movieList.Response.toBoolean()){
                emit(Resource.Success(movieList.toMovieList()))
            }else{
                emit(Resource.Error("No Movie Found!"))
            }

        }catch (e :IOError){
            emit(Resource.Error("No Internet Connection"))
        }
        catch (e:HttpException){
            emit(Resource.Error(e.localizedMessage ?: "No Data"))
        }

    }


}