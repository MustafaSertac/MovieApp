package com.example.movieapp.cleanmovieapp.data.remote

import com.example.movieapp.cleanmovieapp.data.remote.dto.MovieDetailsDto
import com.example.movieapp.cleanmovieapp.data.remote.dto.MoviesDto
import com.example.movieapp.cleanmovieapp.util.Constants
import com.example.movieapp.cleanmovieapp.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {
//http://www.omdbapi.com/?i=tt3896198&apikey=4d888624
@GET(".")
suspend fun getMovies(
    @Query("s") searchString :String,
    @Query("apikey") apiKey :String = API_KEY
) : MoviesDto

    @GET(".")
    suspend fun getMovieDetail(
        @Query("i") imdbId : String,
        @Query("apikey") apiKey: String = API_KEY
    ) : MovieDetailsDto

}