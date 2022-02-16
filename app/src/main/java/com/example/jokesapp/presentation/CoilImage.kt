package com.example.jokesapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.jokesapp.R

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CoilImage(
    iconUrl: String
) {
    Row(
        modifier = Modifier
            .height(150.dp)
            .width(500.dp)
            .padding(20.dp)
            .background(Color.Cyan)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    )  {
        ShowImage()
        ShowImage()
        ShowImage()
    }
}

val images = arrayOf(
    R.drawable.cat1,
    R.drawable.cat11,
    R.drawable.cat13,
    R.drawable.cat14,
    R.drawable.cat2,
    R.drawable.cat5,
    R.drawable.cat7
)

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ShowImage() {

    val random = (0..6).random()
    Image(painter = painterResource(id = images[random]), contentDescription = "")
}