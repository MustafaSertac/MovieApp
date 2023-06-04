package com.example.movieapp.cleanmovieapp.presentation.movie_details.views


import com.example.movieapp.cleanmovieapp.domain.model.MovieDetail

class MovieDetailState(
    val isLoading:Boolean=false,
    val  movie:MovieDetail ?=null,
    val error:String="",
)
