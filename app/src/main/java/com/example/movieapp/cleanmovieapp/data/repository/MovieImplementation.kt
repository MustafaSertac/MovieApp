package com.example.movieapp.cleanmovieapp.data.repository

import com.example.movieapp.cleanmovieapp.data.remote.MovieAPI
import com.example.movieapp.cleanmovieapp.data.remote.dto.MovieDetailsDto
import com.example.movieapp.cleanmovieapp.data.remote.dto.MoviesDto
import com.example.movieapp.cleanmovieapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieImplementation @Inject constructor(private val api:MovieAPI):MovieRepository {
    override suspend fun getMovies(search: String): MoviesDto {
   return api.getMovies(searchString = search)
    }

    override suspend fun getMovieDetails(imdbId: String): MovieDetailsDto {
        return api.getMovieDetails(imdbString = imdbId)
    }
}