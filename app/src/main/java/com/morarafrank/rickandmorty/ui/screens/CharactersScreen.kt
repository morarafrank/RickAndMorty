package com.morarafrank.rickandmorty.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.morarafrank.rickandmorty.R
import com.morarafrank.rickandmorty.ui.RickAndMortyViewModel
import com.morarafrank.rickandmorty.ui.screens.composables.CharactersUi
import com.morarafrank.rickandmorty.ui.screens.composables.CharactersUiData
import com.morarafrank.rickandmorty.ui.screens.composables.EmptyCharactersUi
import com.morarafrank.rickandmorty.ui.screens.composables.LoadingCharactersUi
import com.morarafrank.rickandmorty.ui.screens.composables.SingleCharacterCard
import com.morarafrank.rickandmorty.ui.theme.Typography
import com.morarafrank.rickandmorty.utils.CharactersUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersScreen(
    modifier: Modifier = Modifier,
    rickAndMortyViewModel: RickAndMortyViewModel = hiltViewModel(),
    navigateToCharacter: (id: Int) -> Unit
) {

    val charactersUiState by rickAndMortyViewModel
        .charactersUiState.collectAsStateWithLifecycle()

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

    var searchText by remember{mutableStateOf("")}


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
//                    Text(
//                        text = stringResource(R.string.app_name),
//                        style = Typography.bodyLarge
//                    )

                    OutlinedTextField(
                        value = searchText,
                        onValueChange = { searchText = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp, end = 16.dp)
                        ,
                        placeholder = { Text("Search Characters", style = Typography.bodyMedium) },
                        trailingIcon = {
                            if (searchText.isNotEmpty()) {
                                IconButton(onClick = { searchText = "" }) {
                                    Icon(
                                        painter = painterResource(R.drawable.ic_close),
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    )

                },
                actions = {
//                    IconButton(onClick = {}) {}
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
                        modifier = modifier.padding(it),
                        characters = characters,
                        navigateToCharacter = {
                            navigateToCharacter(it)
                        },
                        listState = gridState,
                        searchText = searchText

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