package com.example.jokesapp.domain

import com.example.jokesapp.data.Joke
import com.example.jokesapp.util.Resource
import kotlinx.coroutines.flow.Flow

class GetJoke(
    private val repository: JokeRepository
) {
    operator fun invoke(): Flow<Resource<Joke>> {
        return repository.getRandomJoke()
    }
}