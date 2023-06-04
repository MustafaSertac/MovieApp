package com.example.movieapp.cleanmovieapp.presentation.movie_details.views

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.cleanmovieapp.domain.model.MovieDetail
import com.example.movieapp.cleanmovieapp.domain.use_case.get_movie_details.GetMovieDetaisUseCase
import com.example.movieapp.cleanmovieapp.util.Constants
import com.example.movieapp.cleanmovieapp.util.Constants.IMDB_ID
import com.example.movieapp.cleanmovieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel

    @Inject constructor(
        private val stateHandle: SavedStateHandle,
        private val getMovieDetaisUseCase: GetMovieDetaisUseCase
    ):ViewModel(){
        private val _state= mutableStateOf<MovieDetailState>(MovieDetailState())
    val state: State<MovieDetailState> =_state

    init {
        stateHandle.get<String> (IMDB_ID)?.let {
            getMovieDetail(it)
            Log.e("error",it)
        }
    }

    private fun getMovieDetail(imdbId:String){
        getMovieDetaisUseCase.executGetMovieDetails(imdbId = imdbId).onEach {
            when(it){
                is Resource.Success->{
_state.value=MovieDetailState(movie=it.data)
                }
                is Resource.Error->{
                    _state.value=MovieDetailState(error =it.message ?: "Error!")

                }
                is Resource.Loading->{
                    _state.value=MovieDetailState(isLoading = true)

                }
            }
        }.launchIn(viewModelScope)
    }
}