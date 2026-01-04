package com.morarafrank.rickandmorty.ui.screens.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/*
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
}*/

@Composable
fun CharactersUi(
    modifier: Modifier = Modifier,
    characters: CharactersUiData,
    listState: LazyGridState,
    navigateToCharacter: (id: Int) -> Unit,
    searchText: String
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        state = listState,
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        items(characters.characters.filter {
            it.name.contains(searchText, ignoreCase = true)
        }) { character ->
            SingleCharacterCard(
                character = character,
                onClick = {
                    navigateToCharacter(character.id)
                }
            )
        }

        // Pagination loader
        if (characters.isLoadingMore) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}
