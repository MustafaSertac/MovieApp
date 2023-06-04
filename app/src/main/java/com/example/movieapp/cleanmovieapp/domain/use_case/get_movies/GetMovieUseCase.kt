package com.example.movieapp.cleanmovieapp.domain.use_case.get_movies



import com.example.movieapp.cleanmovieapp.data.remote.dto.toMoviewList
import com.example.movieapp.cleanmovieapp.domain.model.Movie
import com.example.movieapp.cleanmovieapp.domain.repository.MovieRepository
import com.example.movieapp.cleanmovieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(private val repository:MovieRepository) {

    fun executGetMovies(search: String): Flow<Resource<List<Movie>>> = flow{
        try {
emit(Resource.Loading())
            val movieList=repository.getMovies(search =search )
            if(movieList.Response.equals("True")){
emit(Resource.Success(movieList.toMoviewList()))
            }
            else {
                emit(Resource.Error("No Movie Found"))

            }
        }
        catch (e:IOError){
            emit(Resource.Error("No Internet Connnection"))

        }
        catch (e:HttpException){
            emit(Resource.Error(e.localizedMessage ?:"Error"))

        }
    }
}