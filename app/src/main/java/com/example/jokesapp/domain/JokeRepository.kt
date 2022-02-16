package com.example.jokesapp.domain

import com.example.jokesapp.data.Joke
import com.example.jokesapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface JokeRepository {
    fun getRandomJoke(): Flow<Resource<Joke>>
}