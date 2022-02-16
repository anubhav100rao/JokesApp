package com.example.jokesapp.data

import com.example.jokesapp.domain.JokeRepository
import com.example.jokesapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

class JokeRepositoryImpl(
    private val api: JokeApi
): JokeRepository {
    override fun getRandomJoke(): Flow<Resource<Joke>> = flow {
        emit(Resource.Loading<Joke>())
        try {
            val joke = api.getRandomJoke()
            emit(Resource.Success<Joke>(joke))
        } catch (e: HttpException) {
            emit(Resource.Error<Joke>(
                message = "Something went wrong"
            ))
        } catch (e: IOException) {
            emit(Resource.Error<Joke>(
                message = "Couldn't reach server, check your internet connection."
            ))
        }
    }

}