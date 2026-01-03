package com.morarafrank.rickandmorty.ui.screens.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.morarafrank.rickandmorty.domain.CharacterResponse
import com.morarafrank.rickandmorty.ui.theme.Typography

@Composable
fun SingleCharacterCard(
    character: CharacterResponse,
    onClick: () -> Unit

) {
    Card(modifier = Modifier
//        .fillMaxWidth()
//        .height(120.dp)
        .size(120.dp)
        .clickable { onClick() }
        .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp),

        ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = rememberImagePainter(
                    data = character.image,
                    builder = {
//                        placeholder(R.drawable.placeholder_image)
//                        error(R.drawable.error_image)
//                        transformations(CircleCropTransformation())

                    }
                ),
                contentDescription = "Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Column(
                modifier = Modifier.padding(4.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = character.name,
                    style = Typography.bodyMedium
                )

            }
        }
    }
}