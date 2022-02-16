package com.example.jokesapp.presentation

import com.example.jokesapp.data.Joke

data class JokeState(
    val joke: Joke? = null,
    val isLoading: Boolean = false
)
