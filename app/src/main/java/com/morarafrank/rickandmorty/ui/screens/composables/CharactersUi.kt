package com.morarafrank.rickandmorty.ui.screens.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.morarafrank.rickandmorty.domain.CharacterResponse

@Composable
fun CharactersUi(
    modifier: Modifier = Modifier,
    characters: List<CharacterResponse>,
    navigateToCharacter: (Int) -> Unit
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {

        items(characters) { character ->
            SingleCharacterCard(
                character = character,
                onClick = {
                    navigateToCharacter(character.id)
                }
            )
        }
    }
}