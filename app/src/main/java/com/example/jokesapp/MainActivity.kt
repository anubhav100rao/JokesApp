package com.example.jokesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jokesapp.data.Joke
import com.example.jokesapp.presentation.JokeItem
import com.example.jokesapp.presentation.JokeViewModel
import com.example.jokesapp.ui.theme.JokesAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JokesAppTheme {
                val viewModel: JokeViewModel = hiltViewModel()
                val state = viewModel.state.value
                val scaffoldState = rememberScaffoldState()
                LaunchedEffect(key1 = true) {
                    viewModel.eventFlow.collectLatest { event ->
                        when(event) {
                            is JokeViewModel.UIEvent.ShowSnackbar -> {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = event.message
                                )
                            }
                        }
                    }
                }
                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                        DefaultAppBar()
                    }
                ) {
                    Column(
                        modifier = Modifier
                            .background(MaterialTheme.colors.background)

                    ) {
                        Spacer(modifier = Modifier.height(24.dp))
                        Button(
                            onClick = viewModel::onGetJokeClick,
                            Modifier.padding(20.dp)
                        ) {
                            Text(
                                text = "Get New Joke",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                fontSize = 20.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(50.dp))
                        val joke = state.joke

                        joke?.also {
                            JokeItem(joke = it)
                        }
                        
                        Spacer(modifier = Modifier.height(50.dp))
                        if(state.isLoading) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DefaultAppBar() {
    TopAppBar(
        title = {
            Text(text = "JokesApp")
        }
    )
}