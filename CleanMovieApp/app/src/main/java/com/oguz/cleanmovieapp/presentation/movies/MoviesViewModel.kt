package com.oguz.cleanmovieapp.presentation.movies

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oguz.cleanmovieapp.domain.use_case.get_movies.GetMoviesUseCase
import com.oguz.cleanmovieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
): ViewModel() {

    /**
     * mutable olanı private yapıcam birini de inmutable yapıp public yapıcam view tarafından public e ulasıcapım
     * böylece değiştirilmedinden emin olacagım
     */
    private val _state = mutableStateOf<MoviesState>(MoviesState())
    val state : State<MoviesState> = _state

    private var job : Job? = null
    //TODO:Search tarafında çok hızlı search yapılabilir. Cevap gelmeden kullanıcı başka birşey arayabilir.
    // ya da her bir harf yazdıgınız da bişey aransın isteyebilirsiniz belki onun için bir job a başlamadan
    // daha önce bir job varsa onun iptal edilmesini isteyebilirsiniz.



    init {
        getMovies(_state.value.search) //TODO: ilk search screen i batman oldugu ıcın onu getirecek bana
    }




    private fun getMovies(search:String){

        job?.cancel()

        //TODO: executeGetMovies -> Flow oldugu ıcın onEach kullanıyoruz.
        getMoviesUseCase.executeGetMovies(search).onEach {

            when(it){

                is Resource.Success -> {

                    _state.value = MoviesState(movies = it.data ?: emptyList())

                }
                is Resource.Loading -> {
                    _state.value = MoviesState(isLoading = true)
                }
                is  Resource.Error -> {
                    _state.value = MoviesState(error = it.message ?: "Error")
                    //TODO:_state.value = _state.value.copy() - > Böyle diyerek yeni bir instance olusturmadan halledebiliriz.

                }
            }

        }.launchIn(viewModelScope)

    }

    //TODO: Kullanıcı etkileşime geçtiğinde çağırılır
    fun onEvent(event: MoviesEvent){
        when(event){
            is MoviesEvent.Search -> {
                getMovies(event.searchString)
            }
        }
    }
}