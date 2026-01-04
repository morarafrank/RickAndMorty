package com.morarafrank.rickandmorty.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.morarafrank.rickandmorty.R
import com.morarafrank.rickandmorty.ui.RickAndMortyViewModel
import com.morarafrank.rickandmorty.ui.screens.composables.CharactersUi
import com.morarafrank.rickandmorty.ui.screens.composables.EmptyCharactersUi
import com.morarafrank.rickandmorty.ui.screens.composables.LoadingCharactersUi
import com.morarafrank.rickandmorty.ui.theme.Typography
import com.morarafrank.rickandmorty.utils.CharactersUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersScreen(
    modifier: Modifier = Modifier,
    rickAndMortyViewModel: RickAndMortyViewModel = hiltViewModel(),
    navigateToCharacter: (id: Int) -> Unit
) {

    val charactersUiState by rickAndMortyViewModel.charactersUiState.collectAsStateWithLifecycle()

    val gridState = rememberLazyGridState()


    LaunchedEffect(Unit) {
        rickAndMortyViewModel.loadCharacters()
    }

    LaunchedEffect(gridState) {
        snapshotFlow {
            gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
        }.collect { lastVisibleIndex ->
            val totalCount = gridState.layoutInfo.totalItemsCount
            if (lastVisibleIndex == totalCount - 1) {
                rickAndMortyViewModel.loadCharacters()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        style = Typography.bodyLarge
                    )

                },
                actions = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon"
                        )
                    }
                }
            )
        },
        content = {

            when(charactersUiState){
                is CharactersUiState.Idle -> {
                    EmptyCharactersUi(
                        modifier = modifier,
                        errorMessage = "Welcome to Rick and Morty App. Please initiate a search to find characters.",
                        onRetry = {

                        }
                    )
                }
                is CharactersUiState.Loading -> {
                    LoadingCharactersUi(
                        modifier = modifier.padding(it),
                        size = 150.dp,
                        title = "Loading Characters...",
                        animation = R.raw.loading_anim0
                    )
                }
                is CharactersUiState.Success -> {

                    val characters = (charactersUiState as CharactersUiState.Success).data

                    CharactersUi(
                        modifier = modifier
                            .padding(it),
                        characters = characters,
                        listState = gridState,
                        navigateToCharacter = { characterId ->
                            navigateToCharacter(characterId)
                        }
                    )

                }
                is CharactersUiState.Error -> {
                    val errorMessage = (charactersUiState as CharactersUiState.Error).message
                    EmptyCharactersUi(
                        modifier = modifier.padding(it),
                        errorMessage = errorMessage,
                        onRetry = {
//                            rickAndMortyViewModel.fetchCharacters()
                        }
                    )
                }
            }
        },
    )
}