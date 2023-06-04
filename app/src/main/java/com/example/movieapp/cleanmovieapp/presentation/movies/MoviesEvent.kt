package com.example.movieapp.cleanmovieapp.presentation.movies



sealed  class MoviesEvent {
    data class search(val search: String):MoviesEvent()
}