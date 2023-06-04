package com.example.movieapp.cleanmovieapp.domain.use_case.get_movie_details

import com.example.movieapp.cleanmovieapp.data.remote.dto.toMovieDetails

import com.example.movieapp.cleanmovieapp.domain.model.MovieDetail
import com.example.movieapp.cleanmovieapp.domain.repository.MovieRepository
import com.example.movieapp.cleanmovieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetMovieDetaisUseCase @Inject constructor(private val repository: MovieRepository ) {
    fun executGetMovieDetails(imdbId: String): Flow<Resource<MovieDetail>> = flow{
        try {
            emit(Resource.Loading())
             val movieDetail=repository.getMovieDetails(imdbId = imdbId)
   emit(Resource.Success(movieDetail.toMovieDetails()))

        }
        catch (e: IOError){
            emit(Resource.Error("No Internet Connnection"))

        }
        catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?:"Error"))

        }
    }
}