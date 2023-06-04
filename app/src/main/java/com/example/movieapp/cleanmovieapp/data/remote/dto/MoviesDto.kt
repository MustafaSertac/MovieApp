package com.example.movieapp.cleanmovieapp.data.remote.dto

import com.example.movieapp.cleanmovieapp.domain.model.Movie

data class MoviesDto(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)
fun MoviesDto.toMoviewList():List<Movie> {

    return Search.map { search -> Movie(search.Poster,search.Title,search.Year,search.imdbID) }
}