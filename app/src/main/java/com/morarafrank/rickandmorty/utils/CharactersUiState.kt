package com.morarafrank.rickandmorty.utils

import com.morarafrank.rickandmorty.domain.CharacterResponse
import com.morarafrank.rickandmorty.domain.CharactersResponse

sealed class CharactersUiState {
    object Idle : CharactersUiState()
    object Loading : CharactersUiState()
    data class Success(val data: List<CharacterResponse>) : CharactersUiState()
    data class Error(val message: String) : CharactersUiState()
}