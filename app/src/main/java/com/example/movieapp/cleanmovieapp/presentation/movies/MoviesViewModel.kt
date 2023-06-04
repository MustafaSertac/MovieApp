package com.example.movieapp.cleanmovieapp.presentation.movies

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.cleanmovieapp.domain.use_case.get_movies.GetMovieUseCase
import com.example.movieapp.cleanmovieapp.util.Resource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val getMovieuseCase:GetMovieUseCase,

    private var job : Job? = null
    ): ViewModel()

{
    private val _state= mutableStateOf<MoviesState>(MoviesState())
    val state: State<MoviesState> =_state
    init{
getMovies(_state.value.search)
    }


    private fun     getMovies(search:String){
        job?.cancel()
        job = getMovieuseCase.executGetMovies(search).onEach {
            when(it){
                is Resource.Success->{
                    _state.value = MoviesState(movies = it.data ?: emptyList())
                }
                is Resource.Error->{
                    _state.value = MoviesState(error= it.message ?: "Error!")

                }
                is Resource.Loading->{
                    _state.value = MoviesState(isLoading = true)

                }

            }

        }.launchIn(viewModelScope)
    }
    fun onEvent(event:MoviesEvent){

    }
    }