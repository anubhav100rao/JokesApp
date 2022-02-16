package com.example.jokesapp.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokesapp.domain.GetJoke
import com.example.jokesapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class JokeViewModel @Inject constructor(
    private val getJoke: GetJoke
): ViewModel() {
    private val _state = mutableStateOf(JokeState())
    val state: State<JokeState> = _state

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchJoke: Job? = null

    fun onGetJokeClick() {
        println("Clicked")
        searchJoke?.cancel()
        searchJoke = viewModelScope.launch {
            getJoke()
                .onEach { result ->
                    when(result) {
                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                joke = result.data,
                                isLoading = false
                            )
                        }
                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                joke = result.data,
                                isLoading = false
                            )
                            _eventFlow.emit(UIEvent.ShowSnackbar(
                                result.message ?: "Unknown error"
                            ))
                        }
                        is Resource.Loading -> {
                            _state.value = state.value.copy(
                                joke = result.data,
                                isLoading = true
                            )
                        }
                    }
                }.launchIn(this)
        }
    }



    sealed class UIEvent {
        data class ShowSnackbar(val message: String): UIEvent()
    }
}