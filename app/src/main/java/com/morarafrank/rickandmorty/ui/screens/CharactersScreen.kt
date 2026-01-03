package com.morarafrank.rickandmorty.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
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
//@Preview(showBackground = true)
fun CharactersScreen(
    modifier: Modifier = Modifier,
    rickAndMortyViewModel: RickAndMortyViewModel = hiltViewModel()
) {

//    val context = LocalContext.current
    val charactersUiState by rickAndMortyViewModel.charactersUiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        rickAndMortyViewModel.loadCharacters()
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        style = Typography.bodyMedium
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
                        size = 100.dp,
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
//                            rickAndMortyViewModel.onCharacterSelected(it)
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