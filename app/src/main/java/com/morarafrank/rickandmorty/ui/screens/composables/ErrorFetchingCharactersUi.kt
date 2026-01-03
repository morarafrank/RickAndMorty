package com.morarafrank.rickandmorty.ui.screens.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.morarafrank.rickandmorty.ui.theme.Typography


@Composable
fun IdleCharactersUi(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement
            .spacedBy(12.dp, Alignment.CenterVertically)
    ) {

        Text(
            text = "Welcome to the RickAndMorty App",
            style = Typography.bodyLarge
        )
        Text(
            text = "Search for Rick and Morty characters to get started",
            style = Typography.bodyMedium
        )

    }
}