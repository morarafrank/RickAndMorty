package com.morarafrank.rickandmorty.ui.screens.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.morarafrank.rickandmorty.R
import com.morarafrank.rickandmorty.domain.CharacterResponse
import com.morarafrank.rickandmorty.ui.theme.Typography
import com.morarafrank.rickandmorty.utils.UiUtils

//import androidx.core.R

@Composable
fun CharacterDetailsUi(
    modifier: Modifier = Modifier,
    character: CharacterResponse
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 8.dp, end = 8.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = rememberImagePainter(
                data = character.image,
                builder = {
                    placeholder(R.drawable.placeholder_image)
                    error(R.drawable.error_image)
//                    transformations(CircleCropTransformation())

                }
            ),
            contentDescription = "Image",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
//                .size(100.dp)
                .clip(RoundedCornerShape(8.dp))
                .padding(4.dp)
        )

        Text(
            text = "Name: ${character.name}",
            modifier = Modifier.padding(4.dp),
            style = Typography.bodyLarge
        )

        Text(
            text = "Status: ${character.status}",
            modifier = Modifier.padding(4.dp),
            style = Typography.bodyMedium
        )

        Text(
            text = "Species: ${character.species}",
            modifier = Modifier.padding(4.dp),
            style = Typography.bodyMedium
        )

        Text(
            text = "Type: ${character.type}",
            modifier = Modifier.padding(4.dp),
            style = Typography.bodyMedium
        )

        Text(
            text = "Gender: ${character.gender}",
            modifier = Modifier.padding(4.dp),
            style = Typography.bodyMedium
        )

        Text(
            text = "Origin: ${character.origin.name}",
            modifier = Modifier.padding(4.dp),
            style = Typography.bodyMedium
        )

        Text(
            text = "Location: ${character.location.name}",
            modifier = Modifier.padding(4.dp),
            style = Typography.bodyMedium
        )

        Text(
            text = "Created on: ${UiUtils.formatLastUpdated(character.created)}",
            modifier = Modifier.padding(4.dp),
            style = Typography.bodyMedium
        )

    }
}