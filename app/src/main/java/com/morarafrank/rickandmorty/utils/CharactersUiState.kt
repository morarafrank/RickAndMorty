package com.morarafrank.rickandmorty.utils

import com.morarafrank.rickandmorty.domain.CharacterResponse
import com.morarafrank.rickandmorty.domain.CharactersResponse
import com.morarafrank.rickandmorty.ui.screens.composables.CharactersUiData

sealed class CharactersUiState {
    object Idle : CharactersUiState()
    object Loading : CharactersUiState()
    data class Success(val data: CharactersUiData) : CharactersUiState()
    data class Error(val message: String) : CharactersUiState()
}

sealed class CharacterUiState {
    object Loading : CharacterUiState()
    data class Success(val character: CharacterResponse) : CharacterUiState()
    data class Error(val message: String) : CharacterUiState()
}
