package com.morarafrank.rickandmorty.ui.screens.composables

import com.morarafrank.rickandmorty.domain.CharacterResponse

data class CharactersUiData(
    val characters: List<CharacterResponse>,
    val page: Int,
    val hasNextPage: Boolean,
    val isLoadingMore: Boolean
)
