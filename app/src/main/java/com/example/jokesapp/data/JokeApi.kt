package com.example.jokesapp.data

import retrofit2.http.GET

interface JokeApi {
    @GET("jokes/random")
    suspend fun getRandomJoke(): Joke

    companion object {
        const val BASE_URL = "https://api.chucknorris.io/"
    }
}