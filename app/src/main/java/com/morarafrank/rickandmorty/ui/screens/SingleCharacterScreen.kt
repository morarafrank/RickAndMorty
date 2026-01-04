package com.morarafrank.rickandmorty.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.morarafrank.rickandmorty.R
import com.morarafrank.rickandmorty.ui.RickAndMortyViewModel
import com.morarafrank.rickandmorty.ui.screens.composables.CharacterDetailsUi
import com.morarafrank.rickandmorty.ui.screens.composables.EmptyCharactersUi
import com.morarafrank.rickandmorty.ui.screens.composables.ErrorUi
import com.morarafrank.rickandmorty.ui.screens.composables.LoadingCharactersUi
import com.morarafrank.rickandmorty.ui.theme.Typography
import com.morarafrank.rickandmorty.utils.CharacterUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingleCharacterScreen(
    modifier: Modifier = Modifier,
    id: Int,
    navigateBack: () -> Unit,
    viewModel: RickAndMortyViewModel = hiltViewModel()
) {


    LaunchedEffect(Unit) {
        viewModel.loadCharacter(id)
    }

    val characterUiState by viewModel.characterUiState.collectAsState()


    Scaffold(
        topBar = {
            TopAppBar(
                title = {

                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navigateBack()
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = "Back Arrow"
                        )
                    }
                },

            )
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
//                    .padding(8.dp)
            ) {

                when(characterUiState){

                    is CharacterUiState.Loading -> {
                        LoadingCharactersUi(
                            size = 200.dp,
                            title = "Loading Character...",
                            animation = R.raw.loading_anim0
                        )
                    }
                    is CharacterUiState.Success -> {

                        val character = (characterUiState as CharacterUiState.Success).character
                        CharacterDetailsUi(
                            character = character
                        )
                    }
                    is CharacterUiState.Error -> {
                       val errorMessage = (characterUiState as CharacterUiState.Error).message

                        ErrorUi(
                            errorMessage = errorMessage,
                            onRetry = {
                                viewModel.loadCharacter(id)
                            }
                        )
                    }
                }
            }
        }
    )
}