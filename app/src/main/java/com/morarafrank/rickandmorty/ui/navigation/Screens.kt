package com.morarafrank.rickandmorty.ui.navigation

sealed class Screens (val route: String){
    object CharactersScreen : Screens("characters_screen")
    object SingleCharacterScreen : Screens("single_character_screen")
}